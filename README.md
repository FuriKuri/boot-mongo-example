# boot-mongo-example

Simple project for some docker stuff

## customer
```
# request data
$ curl http://localhost:8080/

# add customer
$ curl -H "Content-Type: application/json" \
	-X POST \
	-d '{"firstName":"Theo","lastName":"Pack"}' \
	http://localhost:8080
```

## hash

## post

```
# show all posts
$ curl http://localhost:8080

# add new post
$ curl http://localhost:8080/?msg=bla%20bla
```