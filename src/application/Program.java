/* Fazer um programa para ler os dados (nome, email e salário) de funcionários a partir de um arquivo em oramato .csv
 * Em seguida mostrar, em ordem alfabética, o email dos funcionários cujo salário seja superior a um dado valor fornecido pelo usuário.
 * Mostrar tambpem a soma dos salários dos funcionários cujo nome começa com a letra 'M'.
 */


package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String filePath = sc.next();
		System.out.print("Enter salary: ");
		Double salary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
		
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.println("Email of people whose salary is more than " + salary + ":");
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail())
					.sorted() // Não é necessário colocar todos esses comandos na função: .sorted((s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()))
					.collect(Collectors.toList());
			emails.forEach(System.out::println);
			
			System.out.print("Sum of salary of people whose name starts with 'M': ");
			Double SumSalaryNamesWithM = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println(String.format("%.2f", SumSalaryNamesWithM));
					
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		sc.close();

	}

}
