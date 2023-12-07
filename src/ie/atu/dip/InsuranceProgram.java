package ie.atu.dip;

//Insurance program to calculate the cost for an optional car based on per accident 


import java.util.Scanner;

public class InsuranceProgram {

	static int basicInsurance = 500;
	static int under25Charge = 100;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter your age: ");
		int age = input.nextInt();
		System.out.print("\n How many accidents have you had?");
		int accidents = input.nextInt();
		totalInsurance(age, accidents);

		input.close();
	}

	public static void totalInsurance(int age, int accidents) {
		int totalInsurance = calculateTotalInsurance(age, accidents);
		insuranceCost(accidents, totalInsurance);
	}

	public static int calculateTotalInsurance(int age, int accidents) {

		int totalInsurance = basicInsurance;
		if (age < 25) {
			totalInsurance += under25Charge;
			System.out.println("Additional surcharge " + under25Charge);
		}
		int accidentSurcharge = insuranceCost(accidents, totalInsurance);
		if (accidentSurcharge == -1) {
			System.out.println("No insurance");
			return 0;

		} else {
			System.out.println("No additional surcharge");
		}
		totalInsurance += addedCPA(accidents);
		return totalInsurance;
	}

	public static int addedCPA(int accidents) {
		int surchargeAccident = 0;
		switch (accidents) {
		case 0:
			surchargeAccident = 0;
			break;
		case 1:
			surchargeAccident = 50;
			break;
		case 2:
			surchargeAccident = 125;
			break;
		case 3:
			surchargeAccident = 225;
			break;
		case 4:
			surchargeAccident = 375;
			break;
		case 5:
			surchargeAccident = 575;
			break;
		default:
			System.out.println("No insurance");
			surchargeAccident = 0;
		}
		return surchargeAccident;
	}

	public static int insuranceCost(int accidents, int totalInsurance) {
		if (accidents <= 5) {
			System.out.println("Additional surcharge for accident: " + addedCPA(accidents)
					+ " \ntotal amount to pay: " + totalInsurance);
		} else {
			return -1;
		}
		return totalInsurance;
	}

}
