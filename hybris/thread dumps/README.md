#Analyzing hybris startup

hybris 6 with follow extensions:

- advancedsavedquery
- catalog
- comments
- commons
- core
- deliveryzone
- europe1
- hac
- impex
- maintenanceweb
- mediaweb
- oauth2
- paymentstandard
- platformservices
- processing
- scripting
- testweb
- validation
- workflow
- yempty
- yhacext

Server startup took about  79825 ms (1.3 min). During startup I generated 6 thread dumps and create diagram based on it.

From my point of view, startup takes so much time because there are lot of threads in hybris, despite the list of enabled extensions.
Moreover, count of runnable threads is less than others at same time. I guess that it depends on thread pool configuration. 


![Threads Diagram](https://github.com/hanna-eismant/Java-Mentoring/blob/JaMP/2017w/hybris/thread%20dumps/threads%20diagram.png?raw=true)
