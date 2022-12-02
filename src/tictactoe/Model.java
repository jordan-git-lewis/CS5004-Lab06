package tictactoe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model implements tictactoe.IModel
{
	private String input;
	
	public Model()
	{
		input = "";
	}

	@Override
	public void setString(String i)
	{
		input = i;
	}

	@Override
	public String getString()
	{
		return input;
	}

	@Override public String invertString() {
		StringBuilder inverted = new StringBuilder();
		for(int index = 0; index < this.input.length(); index ++)
			inverted.append(this.input.charAt(this.input.length() - 1 - index));
		return inverted.toString();
	}

	@Override public String removeVowels() {
		Pattern p = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE);
		StringBuilder noVowels = new StringBuilder();
		for(int index = 0; index < this.input.length(); index ++) {
			String letter = String.valueOf(this.input.charAt(index));
			Matcher m = p.matcher(letter);
			if (!m.find())
				noVowels.append(letter);
		}
		return noVowels.toString();
	}

}