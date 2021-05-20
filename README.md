# PIRC-Chatbot 
This project uses the [PircBot java framework](http://www.jibble.org/pircbot.php) to create an IRC bot, The bot pulls json information from REST API's and parses it using gson.

Weather information is pulled off [open weather map's API](https://openweathermap.org/API) 

Bitcoin information is pulled off [coindesk's API](https://api.coindesk.com/v1/bpi/currentprice.json)

# How To use:
- When run will add chatbot to [freenode IRC network](https://webchat.freenode.net/)
- Join the server #WeatherBot-777 (make sure to include #)
- type "commands" to view avaliable commands
  - getweather / what's the weather - Will prompt the user to enter a zipcode or cityname and return the forecast
  - bitcoin / what's the price of bitcoin - prints the current price info from coinbase in dollars, sterling, euros.
  - exit - removes the bot from the channel

