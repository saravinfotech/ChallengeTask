# ChallengeTask

SlowNetwork.kt --> Uncommented the codes 40-42 & 49-51 which were commented to disable the main thread execution. Now this file is back to the original state preventing these methods to be called from Main thread

CountryViewModel.kt--> 
    Uses MutableLiveData to post the values to the UI
    getCountriesFromFile() function parses the JSON from SlowNetwork on IO thread using coroutines
    
    
CountryDetail --> 🤦🤦‍♂️ added the @SerializedName annotation for the JSON keys


As of now havent integrated the logic to fetch specific country name and country capital. Working on the same, will refactor the class names and variables


Updated the code in ViewModel to use Kotlin filter function to fetch the specific country name based on Country Code. As of now the country code is hardcoded to DEN

Updated the code to fetch the country capital. Still the country name is hardcoded. 
