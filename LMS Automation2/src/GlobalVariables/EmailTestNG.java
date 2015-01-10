package GlobalVariables;

import org.testng.annotations.Test;

public class EmailTestNG 
{

	GlobalDecl GV = new GlobalDecl();
	@Test
	public void Email() throws Exception
	{		
		System.out.println("This is Email class file");

		Zip_and_Attach za = new Zip_and_Attach();

		za.main(null);
	}
}
