# SPTechTest
Technical test for Android position with Scottish Power

Test details as provided by Scottish Power:

Create an Android app that makes use of the following API: https://jsonplaceholder.typicode.com/
 
*General requirements:*
* Use *Retrofit* and any other 3rd party library that you would find useful.
* The app will be tested on a phone with setting *do not keep activities* enabled.
* The app will be tested using a very slow Internet connection.
* We advise to spend *no more than 2h* on the solution. We only wish to check your overall understanding of Android development, usage of Android framework and libraries, and your programming style.
* Please provide a list of recommended future improvements in the app.
 
The minimum viable product consists of one screen – a list of albums.
It should display a list of titles returned by “`/albums`” call (one below another).
 
*Premium requirements:*
* There should be at least one automated instrumentation test using *Espresso*.
* The albums in the list should be sorted by title. (edited)


***RECOMMENDED IMPROVEMENTS***

- Allow user to sort list by UserId or Id rather than just title
- Improve the UI with some pretty designs 
- I suspect my implementation of using the MainActivity as the IAlbumRequestListener could lead to possible memory leaks down the line
- Persist the list of albums to the device
- If we had control over the api allow requests for pages of results rather than all at once to ease network stress
