package com.booleanuk.core;

import java.util.Objects;
import java.util.Scanner;

import static com.booleanuk.core.Menu.printFillingMenu;
import static com.booleanuk.core.Menu.selectItemFromMenu;

public class Order {
    private Basket basket = new Basket();

    public void order() {
        Scanner input = new Scanner(System.in);

        int customerOrManager = customerOrManager(input);

        if (customerOrManager == 1) {
            customerMenu(input);
        }

        else if (customerOrManager == 2){
            managerMenu(input);
        }
    }

    private int customerOrManager(Scanner input){
        System.out.println("""
                Howdy!
                Welcome to Bob's bagels. Select one of the following options
                
                1. I am a customer
                
                2. I am a manager
                
                3. Exit
                """);
        while (true)
        {
            int customerOrManager = input.nextInt();

            if (customerOrManager == 1){
                return 1;
            }

            else if (customerOrManager == 2){
                return 2;
            }

            else if (customerOrManager == 3){
                return 3;
            }

            else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void customerMenu(Scanner input){
        System.out.println("Welcome to Bob's bagels. Please select one of the following options:");
        while (true){
            System.out.println("1. Add item to your basket.");
            System.out.println("2. Remove item from your basket.");
            System.out.println("3. View your basket.");
            System.out.println("4. Proceed to checkout.\n");

            switch (input.nextInt()){
                case 1: {
                    Item chosenItem = selectItemFromMenu();

                    if (chosenItem == null)
                        break;

                    if (Objects.equals(chosenItem.name, "Bagel")){
                        Bagel bagel = new Bagel(chosenItem.sku, chosenItem.price, chosenItem.name, chosenItem.variant);
                        bagel.addFillingsToBagel(input);
                        basket.addItemToBasket(bagel);
                        break;
                    }

                    basket.addItemToBasket(chosenItem);
                    break;
                }
                case 2: {
                    basket.removeItemFromBasket();
                    break;
                }
                case 3: {
                    basket.printBasketContent();
                    break;
                }
                case 4: {
                    System.out.println("That will be " + basket.calculateBasketCost() + "$.\nThank you for shopping at Bob's bagels\n");
                    return;
                }
            }
        }
    }

    private void managerMenu(Scanner input){
        System.out.println("You have only one option:");
        while (true){
            System.out.println("1. Change basket size.\n");

            switch (input.nextInt()){
                case 1: {
                    System.out.println("Enter new size of the basket.");
                    int newBasketSize = input.nextInt();
                    basket.changeBasketSize(newBasketSize);
                    break;
                }
                default: {
                    System.out.println("That is not a viable option, is it?");
                }
            }
        }
    }




}
