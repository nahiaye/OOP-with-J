package com.company;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    private List<Movie> movies;
    private List<Transaction> transactions;
    private void initMoviesList() {
        this.movies = new ArrayList<>();
        this.movies.add(new DVDMovie("Star Wars", 9));
        this.movies.add(new DVDMovie("Star Trek", 9));
        this.movies.add(new BluRayMovie("Star Wars", 9));
        this.movies.add(new BluRayMovie("Star Trek", 9));
    }
        public StoreManager() {
            this.transactions = new ArrayList<>();
            this.initMoviesList();
        }
        public void sell(Customer customer) {
        // null pointer check
        if (this.movies == null) {
            System.err.println("Sorry, no more movies...");
            return;
        }
        // empty movies list check
        if (this.movies.size() == 0) {
            System.err.println("Sorry, no more movies...");
            return;
        }
        // create new transaction
        Transaction transaction = new Transaction(customer);
        // get first movie available
        Movie movieBeingSold = this.movies.get(0);
        // try to sell the movie
        if (transaction.addMovie(movieBeingSold)) {
            //movie sold
            System.out.printf("Movie sold: %s%n", movieBeingSold.getTitle());
            // decrease movie stock
            movieBeingSold.setStock(
                    movieBeingSold.getStock() - 1
            );
            // add transaction to history
            this.transactions.add(transaction);
            // update customer points
            customer.setPoints(
                    customer.getPoints() + transaction.getLoyaltyPoints()
            );
            return;
        } //if
        // that movie out of stock, remove from the store and try to sell the other movie
        this.movies.remove(0);
        sell(customer);
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

}
