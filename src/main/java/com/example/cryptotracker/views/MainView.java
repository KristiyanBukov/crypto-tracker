package com.example.cryptotracker.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        Grid<Crypto> cryptoGrid = new Grid<>(Crypto.class);
        cryptoGrid.setColumns("name", "amount", "value");
        List<Crypto> portfolio = getPortfolio();
        cryptoGrid.setItems(portfolio);

        add(new H1("Crypto Portfolio"), cryptoGrid);
    }

    private List<Crypto> getPortfolio() {
        // Fetch or create your portfolio data here.
        // For now, we'll use some dummy data.
        List<Crypto> portfolio = new ArrayList<>();
        portfolio.add(new Crypto("Bitcoin", 1.0, 40000.0));
        portfolio.add(new Crypto("Ethereum", 10.0, 2000.0));
        return portfolio;
    }

    public class Crypto {
        private String name;
        private double amount;
        private double value;

        public Crypto(String name, double amount, double value) {
            this.name = name;
            this.amount = amount;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public double getAmount() {
            return amount;
        }

        public double getValue() {
            return value;
        }
    }
}