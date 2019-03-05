# JobFinder
This application fetches job description from two api's.The result is displayed in a recycler view.

Operation:
When the app starts its fetched your current location ,stores it and is used to fetch the job when location is used to filter.
It has a search view along with three filters Position, Provider and Location respectively.The filter should be set before searching.
When the job is clicked it opens the detail page about the job.

Uses:
1.Rx Java 
2.Dagger 
3.Retrofit 
4.MVVM pattern 

FlatMap operator of RxJava is used to combine the Api response.It ensures scaleability.

Limitations:
1.Filter should be set before searching the Job.
2.Poor user interface.
3.The URL from one of the api does not load.
4.Does not combine filters.
5.Location may take a little time to load.
6.ViewModel of architecture component not used.
7.Searching ability can be improved.
