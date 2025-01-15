# Hostage Checker
Web API that provides all public information about every hostage from October 7th.\
The response is a simple and detailed JSON list, where every item represents one hostage.

## How to Query
The API has a [single endpoint](https://hostage-checker.onrender.com/hostages) which responds with the following structure: \
![image](https://github.com/user-attachments/assets/c3bbf197-4b44-4f70-ae59-c9b270281415)

## Important Info
- The "*last_updated*" date is relative to Israel's timezone.
- Responses  are cached for **30 minutes** - Please open an issue if it goes above that.
- The API is currently hosted on a free hosting - it may take a few minutes to get a response.
