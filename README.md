# Hostage Checker
Web API that provides all public information about every hostage from October 7th.\
The response of its [single endpoint](https://hostage-checker.onrender.com/hostages) is comprised of the following:
- A detailed list where every item represents an hostage.
- Numerical statistics(Commonly accessed).

## Response Structure
![תמונה](https://github.com/user-attachments/assets/98540148-e983-4048-ad09-97d2037c4760)

## Important Info
- The "*last_updated*" date is relative to Israel's timezone.
- Responses are cached for **30 minutes** - Please open an issue if it goes above that.
- The API is currently hosted on a free hosting - it may take a few minutes to get a response.

## Credits
Behind the scenes there's no sophisticated data tracking, what this service does is to convert obscure data to easily parsable format.\
The current data provider is *N12*.
