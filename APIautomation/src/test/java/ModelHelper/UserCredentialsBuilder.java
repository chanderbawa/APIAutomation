package ModelHelper;

public class UserCredentialsBuilder {

	private static UserCredentials _userCredentials = null;
	public static UserCredentials CreateUserCredentials(String userName, String userPassword)
	{
		System.out.println("Setting values for UserCredentials model");
		_userCredentials = new UserCredentials();
		_userCredentials.UserName =  userName;
		_userCredentials.Password = userPassword;	
		return _userCredentials;
	}

}
