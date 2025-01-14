# Hostage Checker
Web API that provides all public information about every hostage from October 7th.\
The response is a simple and detailed JSON list, where every item represents one hostage.

## How to Query
The API has a [single endpoint](https://hostage-checker.onrender.com/hostages) which responds with the following structure: \
![image](https://github.com/user-attachments/assets/56e3f14d-25f6-4419-af42-c855836ea484)

## Information
- The "*last_update*" field is relative to Israel's timezone.
- The response is cached for **30 minutes** - Please open an issue if it goes above that.
