package com.example.weather

data class Province(val title: String, val cities: List<City>)
data class City(val title: String, val details: LocationDetails)
data class LocationDetails(val latitude: Double, val longitude: Double)

val BRITISH_COLUMBIA_CITIES = listOf(
    City(
        "Vancouver", LocationDetails(
            49.592949450000006, -125.70255696124094,
        )
    ),
    City(
        "Victoria", LocationDetails(
            48.4283182, -123.3649533,
        )
    ),
    City(
        "Surrey", LocationDetails(
            49.1913033, -122.849143,
        )
    ),
    City(
        "Burnaby", LocationDetails(
            49.2433804, -122.972545,
        )
    ),
    City(
        "Richmond", LocationDetails(
            49.163168, -123.137414,
        )
    ),
)

val CALIFORNIA_CITIES = listOf(
    City(
        "Los Angeles", LocationDetails(
            34.0536909, -118.242766
        )
    ),
    City(
        "Los Angeles", LocationDetails(
            32.7174202, -117.1627728
        )
    ),
    City(
        "San Jose", LocationDetails(
            37.3361663, -121.890591,
        )
    ),
    City(
        "San Francisco", LocationDetails(
            37.7790262, -122.419906,
        )
    ),
    City(
        "Oakland", LocationDetails(
            37.8044557, -122.271356,
        )
    )
)

val WASHINGTON_CITIES = listOf(
    City(
        "Seattle", LocationDetails(
            47.6038321, -122.330062,
        )
    ),
    City(
        "Spokane", LocationDetails(
            47.6571934, -117.42351
        )
    ),
    City(
        "Tacoma", LocationDetails(
            47.2455013, -122.438329,
        )
    ),
    City(
        "Vancouver", LocationDetails(
            45.6306954, -122.6744557,
        )
    ),
    City(
        "Bellevue", LocationDetails(
            47.6144219, -122.192337,
        )
    )
)

val ONTARIO_CITIES = listOf(
    City(
        "Toronto", LocationDetails(
            43.6534817, -79.3839347,
        )
    ),
    City(
        "Guelph", LocationDetails(
            46.0227498, -98.2359354,
        )
    ),
    City(
        "Windsor", LocationDetails(
            42.3167397, -83.0373389,
        )
    ),
    City(
        "Kingston", LocationDetails(
            44.230687, -76.481323,
        )
    ),
    City(
        "Hamilton", LocationDetails(
            43.2560802, -79.8728583,
        )
    ),
)

val OTHER_CITIES = listOf(
    City(
        "New York", LocationDetails(
            40.7127281, -74.0060152,
        )
    ),
    City(
        "Cumberland", LocationDetails(
            49.592949450000006, -125.70255696124094,
        )
    ),
    City(
        "Montreal", LocationDetails(
            45.5031824, -73.5698065,
        )
    ),
    City(
        "Mashhad", LocationDetails(
            36.2974945, 59.6059232,
        )
    ),
)

val PROVINCES = listOf(
    Province(
        "British Columbia",
        BRITISH_COLUMBIA_CITIES
    ),
    Province(
        "California",
        CALIFORNIA_CITIES
    ),
    Province(
        "Washington",
        WASHINGTON_CITIES
    ),
    Province(
        "Ontario",
        ONTARIO_CITIES
    ),
    Province(
        "Others",
        OTHER_CITIES
    )
)

