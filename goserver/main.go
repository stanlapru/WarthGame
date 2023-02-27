package main

import (
	"log"
	"net/http"
)

func main() {
	http.HandleFunc("/login", Login)
	http.HandleFunc("/login", Home)
	http.HandleFunc("/login", Refresh)

	log.Fatal(http.ListenAndServe(":8080", nil))
}
