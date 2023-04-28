# currencyBot

## Description
This bot is designed for currency conversions from RUB to another cuurency or vice versa, using information from the Central Bank of Russia. 
The bot uses [telegrambots-spring-boot-starter](https://github.com/rubenlagus/TelegramBots/blob/master/telegrambots-spring-boot-starter/README.md) for starting and initialization and extends TelegramLongPollingBot.

There 3 commands: 
* /start - just greetings a user and ask to write an amount and a currency
* /help - give information how to use this bot
* /currency - give the list of available currency to convert according to the Central Bank of Russia

## Backend
The Repository uses [Jsoup](https://jsoup.org/) to parse the html page of Central Bank and save information into **String** *currencybase*. The method have @Scheduled annotation to update information every hour.
Also, the Repository has 2 methods:
* getListOfAvailableCurrency() - get String with available currency, using **currencybase**. It uses basics String methods - *replaceAll* and *trim*;
* findCurrency() - get String from **currencybase** with ratio RUB/chosen currency. For example: *USD	1	Доллар США	81,7441*. The Service parse it and uses for calculation.

The Service has 2 main methods:
* calculateToRub()
* calculateToChosenCurrency()
Depends on request, it parses a String and make a calculation. To convert into RUB, a String must have 3 parameters - *amount, indication RUB, chosen currency* (100 RUB USD).
To convert rubles to chosen currency, only 2 parameters are requested - *amount, chosen currency* (100 USD)

Both methods parse a String into **ArrayList** and **Array** to get numbers to calculate ratio, then replace "," sign for "." sign to get **BigDecimal** values and create a response, which send to **TelegramBot** class. 
