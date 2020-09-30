### Website Monitor

# An amazing tool to moniter your websites
```
# This project moniters websites by URL.

The project by URL takes parameters from site: 
-> response time 
-> response size
-> response code
-> response message 
-> monitoring period

Than based on parameters that keeps in db (url, monitoring period, 
response time, response code, min response size, max response size, 
substring and status) program validate this data and return it to user.

# Used parallelism for computation website parameters.

```
## User Interface
```
# User Interface it`s just a page with table. 
The Table has four columns: 
-> URLs
-> URLs status
-> About
and substring.

# URL
Monitoring URLs

# Status
The status table cell can have tree conditions - OK, WARNING, CRITICAL.
Each of this status has their own color - green, orange, red.
Also when status is WARNING or CRITICAL the app makes a sound 
(different for WARNING and CRITICAL).

OK - everything is fine

WARNING - response time is equal to response time in db
CRITICAL - response code isn`t 200, response size out of limit 
(min response size, max response size from db), response time out of limit.

# About
Additional information about each status.

# Substring
We are looking for this substring in response. If not found it return CRITICAL.


# User can stop monitoring or restart again. Also information about websites 
every time refreshed (parameter from db - monitoring period). 
Monitoring period you can set only one for all websites.

```
