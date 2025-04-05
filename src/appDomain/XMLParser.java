package appDomain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import exceptions.EmptyQueueException;
import implementations.MyQueue;
import implementations.MyStack;


/**
 * A simple XML validator that checks for proper XML structure.
 * The parser reads XML content and reports any errors found.
 * @author Ethan Van De Woestyne
 */
public class XMLParser
{
	private MyStack<TagInfo> stack;
	private MyQueue<String> errors;

	/**
	 * Inner class to store information about XML tags during parsing.
	 */
	private static class TagInfo
	{
		String name;
		String fullTag;
		int lineNumber;

		/**
		 * Constructs a TagInfo object with the specified tag details.
		 * 
		 * @param name The name of the XML tag
		 * @param fullTag The full content of the tag including attributes
		 * @param lineNumber The line number where the tag appears in the source
		 */
		TagInfo(String name, String fullTag, int lineNumber)
		{
			this.name = name;
			this.fullTag = fullTag;
			this.lineNumber = lineNumber;
		}
	}

	/**
	 * Constructs an XMLParser with empty stack and error queue.
	 */
	public XMLParser()
	{
		stack = new MyStack<>();
		errors = new MyQueue<>();
	}

	/**
	 * Entry point for the application. Reads an XML file and validates its structure.
	 * 
	 * @param args Command line arguments. Expected to contain a single file path to the XML file
	 */
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Usage: java -jar XMLParser.jar <xml file path>");
			return;
		}

		String filePath = args[0];
		try
		{
			String content = new String(Files.readAllBytes(Paths.get(filePath)));
			XMLParser parser = new XMLParser();
			parser.parse(content);
		} catch (IOException e)
		{
			System.out.println("Error reading file: " + filePath);
			e.printStackTrace();
		}
	}

	/**
	 * Parses the provided XML string and validates its tag structure.
	 * The method checks for proper opening and closing of tags and reports any errors found.
	 * Results are printed to standard output.
	 * 
	 * @param xml The XML content to be parsed and validated
	 */
	public void parse(String xml)
	{
		String[] lines = xml.split("\n");

		for (int i = 0; i < lines.length; i++)
		{
			int lineNum = i + 1;
			String line = lines[i].trim();
			if (!line.isEmpty())
			{
				int pos = 0;
				while (pos < line.length())
				{
					int start = line.indexOf('<', pos);
					if (start == -1)
						break;

					int end = line.indexOf('>', start);
					if (end == -1)
					{
						break;
					}

					String tagContent = line.substring(start + 1, end).trim();
					pos = end + 1;

					if (!tagContent.startsWith("?"))
					{
						if (!tagContent.endsWith("/"))
						{
							String tagName = tagContent.split("\\s+")[0];
							if (tagContent.startsWith("/"))
							{
								tagName = tagName.substring(1);
								if (stack.isEmpty())
								{
									errors.enqueue("Error at line: " + lineNum + " </" + tagName
											+ "> is not constructed correctly.");
								}
								else
								{
									TagInfo top = stack.peek();
									if (top.name.equals(tagName))
									{
										stack.pop();
									}
									else
									{
										MyStack<TagInfo> tempStack = new MyStack<>();
										boolean foundMatch = false;

										while (!stack.isEmpty())
										{
											TagInfo currentTag = stack.pop();
											if (currentTag.name.equals(tagName))
											{
												foundMatch = true;
												while (!tempStack.isEmpty())
												{
													TagInfo unclosedTag = tempStack.pop();
													errors.enqueue("Error at line: " + unclosedTag.lineNumber + " <"
															+ unclosedTag.fullTag + "> is not constructed correctly.");
												}
												break;
											}
											tempStack.push(currentTag);
										}

										while (!tempStack.isEmpty())
										{
											stack.push(tempStack.pop());
										}

										if (!foundMatch)
										{
											errors.enqueue("Error at line: " + lineNum + " </" + tagName
													+ "> is not constructed correctly.");
										}
									}
								}
							}
							else
							{
								stack.push(new TagInfo(tagName, tagContent, lineNum));
							}
						}
					}
				}
			}
		}

		while (!stack.isEmpty())
		{
			TagInfo tag = stack.pop();
			errors.enqueue("Error at line: " + tag.lineNumber + " <" + tag.fullTag + "> is not constructed correctly.");
		}

		if (errors.isEmpty())
		{
			System.out.println("XML is syntactically correct.");
		}
		else
		{
			while (!errors.isEmpty())
			{
				try
				{
					System.out.println(errors.dequeue());
				} catch (EmptyQueueException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
