# ForecastApp
I made this app according to requirement that need mobile application that can show curren weather, forescast and search for the city.

# Feature
There is 3 screen with 3 feature 
- **CurrentWeather** main screen that show current weather, location, and humidity
- **Forecast** show 5 day forecast
- **Search** search screen that you can enter (some) city name and it's will show you forcast for 5 day

# Architecture
this application using multi module architecture seperate by layer and feature

this is relation between module

**Note :feature:search use **:domain:currentweather** and **:domain:forecast** because they don't have their own business logic

```
main app - :feature:currentweather -> :domain:currentweather -> :data:currentweather

         - :feature:forecast       -> :domain:forecast       -> :data:forecast
         
         - :feature:search         -> :domain:currentweather
                                      :domain:forecast
```


this is what all module look like :
```
:feature:currentweather
:feature:forecast
:feature:search
:domain:currentweather
:domain:forecast
:data:currentweather
:data:forecast
```
# Tech
> Dependency Injection by Koin

> Jetpack Compose 

I am new into Jetpack Compose but I want to try this out
> ViewBinding

> Navigation Component

I am also new into this Navigation Component. I am not sure what I am doing is the best practice ?


# Unittest
Only tested UseCase in 
```
:domain:currentweather
:domain:forecast
```


# Known Issue
**Navigate Back Problem** - when we navigate back to current weather screen or search screen from forecast screen there is some problem with viewmodel or fragment 

that observe data because when we back to current weather screen or search screen. 

it's triggered on open forecast screen funtion so there is look like we are not going back to previus screen

I am not sure where is this problem came from

I assume that is because viewmodel is still have data on it's own. So when back to current weather screen or search screen it's trigger navigation

# Future
If you want to add new feature like **tempurature map** you just have to create 2 or 3 module depend on your feature
```

:feature:new_feature contain UI part of your feature
:domain:new_feature (optional) contain business logic of your feature (usecase). I think we should create this domain module only when the business logic can/need to reused in other feature
:data:new_feature contain data, model and repository of your feature
```


Or In case We want to implement 7-day forecast we can just change the API PATH in **:data:forecast** and some DATA RESPONSE to match the response of new api path in **:data:forecast**

# Reuseability
```
:domain:currentweather
:domain:forecast
:data:currentweather
:data:forecast
```

these 4 module can be reuse if new feature want to use their business logic like current weather or forcast

