# ChallengeTask

SlowNetwork.kt --> Uncommented the codes 40-42 & 49-51 which were commented to disable the main thread execution. Now this file is back to the original state preventing these methods to be called from Main thread

CountryViewModel.kt--> 
    Uses MutableLiveData to post the values to the UI
    getCountriesFromFile() function parses the JSON from SlowNetwork on IO thread using coroutines
    
    
CountryDetail --> 🤦🤦‍♂️ added the @SerializedName annotation for the JSON keys
