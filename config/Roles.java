package edu.ncsu.csc440.poketrader.config;

/**
 * Defines user roles for Poketrader.
 * Admin is the super-user. All other roles are listed in {@link UserRoles}.
 *
 *Role responsibilities:	
 *   ROLE_ADMIN – full system access
 *   ROLE_USER - limited system access
 */
public class Roles {

	/** Admin role name */
	public static final String ROLE_ADMIN = "Admin";
	public static final String ROLE_USER= "User";

	/**
	 * Defines all non-admin roles in the system.
	 * Adding a value here automatically creates the role in the database at startup.
	 */
	public enum UserRoles {

		/**
		 * Limited access user role
		 */
		User,
		Admin

	}

}
