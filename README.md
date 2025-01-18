# Hostage Checker
Web API that provides all public information about every hostage from October 7th.\
The response of its [single endpoint](https://hostage-checker.onrender.com/hostages) is comprised of the following:
- A detailed list where every item represents an hostage.
- Numerical statistics(Commonly accessed).

## Response Structure
![image](https://github.com/user-attachments/assets/7218b1e6-f7aa-4aba-918f-cb35a17e9d1a)

## Important Info
- The "*last_updated*" date is relative to Israel's timezone.
- Responses  are cached for **30 minutes** - Please open an issue if it goes above that.
- The API is currently hosted on a free hosting - it may take a few minutes to get a response.

## Credits
Behind the scenes there's no sophisticated data tracking, what this service essentially does is to translate data from **N12** into easily readable format.
