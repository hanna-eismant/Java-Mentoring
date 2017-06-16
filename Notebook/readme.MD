## Configure Mongo DB
1. Download and install MongoDB server: https://www.mongodb.com/download-center#community
1. Change path to necessary in `mongod.cfg`. Pay attention, that these catalogs should be created manually before start mongo server.
1. Change path to mongo server executable in `start_mongo.bat`
1. Start mongo server via `start_mongo.bat`

## Use options in program
```
0 - exit
1 - show all notes
2 - find notes by tag 
3 - find notes by tag and text 
4 - remove note
```

Insert selected option -> `<enter>` -> parameters

For instance, to search notes by tag:
```
2
<enter>
test
<enter>
```


