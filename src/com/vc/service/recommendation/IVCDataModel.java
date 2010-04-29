package com.vc.service.recommendation;

import java.util.List;

import org.apache.mahout.cf.taste.common.NoSuchItemException;
import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

public interface IVCDataModel extends Refreshable {

	/**
	 * @return all user IDs in the model, in order
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract LongPrimitiveIterator getUserIDs() throws TasteException;

	/**
	 * @param userID
	 *            ID of user to get prefs for
	 * @return user's preferences, ordered by item ID
	 * @throws NoSuchUserException
	 *             if the user does not exist
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract  PreferenceArray getPreferencesFromUser(String userID) throws TasteException;

	/**
	 * @param userID
	 *            ID of user to get prefs for
	 * @return IDs of items user expresses a preference for
	 * @throws NoSuchUserException
	 *             if the user does not exist
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract FastIDSet getItemIDsFromUser(String userID) throws TasteException;

	/**
	 * @return a {@link List} of all item IDs in the model, in order
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract LongPrimitiveIterator getItemIDs() throws TasteException;

	/**
	 * @param itemID
	 *            item ID
	 * @return all existing {@link Preference}s expressed for that item, ordered
	 *         by user ID, as an array
	 * @throws NoSuchItemException
	 *             if the item does not exist
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract PreferenceArray getPreferencesForItem(String itemID) throws TasteException;

	/**
	 * Retrieves the preference value for a single user and item.
	 * 
	 * @param userID
	 *            user ID to get pref value from
	 * @param itemID
	 *            item ID to get pref value for
	 * @return preference value from the given user for the given item or null
	 *         if none exists
	 * @throws NoSuchUserException
	 *             if the user does not exist
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract Double getPreferenceValue(String userID, String itemID) throws TasteException;

	/**
	 * @return total number of items known to the model. This is generally the
	 *         union of all items preferred by at least one user but could
	 *         include more.
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract int getNumItems() throws TasteException;

	/**
	 * @return total number of users known to the model.
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract int getNumUsers() throws TasteException;

	/**
	 * @param itemIDs
	 *            item IDs to check for
	 * @return the number of users who have expressed a preference for all of
	 *         the items
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 * @throws IllegalArgumentException
	 *             if itemIDs is null, empty, or larger than 2 elements since
	 *             currently only queries of up to 2 items are needed and
	 *             supported
	 * @throws NoSuchItemException
	 *             if an item does not exist
	 */
	public abstract int getNumUsersWithPreferenceFor(String... itemIDs) throws TasteException;

	/**
	 * <p>
	 * Sets a particular preference (item plus rating) for a user.
	 * </p>
	 * 
	 * @param userID
	 *            user to set preference for
	 * @param itemID
	 *            item to set preference for
	 * @param value
	 *            preference value
	 * @throws NoSuchItemException
	 *             if the item does not exist
	 * @throws NoSuchUserException
	 *             if the user does not exist
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract void setPreference(String userID, String itemID, double value) throws TasteException;

	/**
	 * <p>
	 * Removes a particular preference for a user.
	 * </p>
	 * 
	 * @param userID
	 *            user from which to remove preference
	 * @param itemID
	 *            item to remove preference for
	 * @throws NoSuchItemException
	 *             if the item does not exist
	 * @throws NoSuchUserException
	 *             if the user does not exist
	 * @throws TasteException
	 *             if an error occurs while accessing the data
	 */
	public abstract void removePreference(String userID, String itemID) throws TasteException;

	public abstract boolean hasPreferenceValues();

}
