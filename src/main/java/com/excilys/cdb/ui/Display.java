package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.ui.Actions;

public class Display {
	static Scanner sc = new Scanner(System.in);
	
	public static void menuCli() {
		System.out.println("1>> Afficher la liste des Ordinateurs.");
		System.out.println("2>> Afficher la liste des Companies.");
		System.out.println("3>> Afficher le détail d'un ordinateur.");
		System.out.println("4>> Créer un ordinateur");
		System.out.println("5>> Mettre à jour un ordinateur");
		System.out.println("6>> Supprimer un ordinateur");
		System.out.println("0>> Quitter");
	}

	public static void DisplayCli() {
		String repUser = "";
		System.out.println("Bonjour et bienvenue sur CDB. Que souhaitez-vous faire?");
		do {
			Display.menuCli();
			repUser = sc.nextLine().trim();	
			switch (repUser) {
			case "1":
				ComputerService.findAll();
				break;
			case "2":
				//CompanyService.findAllCompanies();
				break;
			case "3":
				Actions.findOneCLI();
				break;
			case "4":
				Actions.addCLI();
				break;
			case "5":
				ComputerService.update(null);
				break;
			case "6":
				Actions.DeleteCLI();
				break;
			default:
				System.out.println("Réponse incorrecte!! Veuillez saisir une autre réponse entre 0 et 6");
				break;
			}
		} while (!repUser.equals("0"));
			System.out.println("GOOD BYE!");
			sc.close();
	}
 }