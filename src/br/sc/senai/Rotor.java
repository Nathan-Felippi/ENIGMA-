package br.sc.senai;

import java.util.Scanner;

public class Rotor {

	Scanner scanner = new Scanner(System.in);

	// Valor das configurações em letras que o usuário digitou.
	private char valorOriginalRotor1;
	private char valorOriginalRotor2;
	private char valorOriginalRotor3;

	// Valor numeral da letra digitada pelo usuário.
	private int rotor1;
	private int rotor2;
	private int rotor3;

	// Palavra final que foi feito a criptografia, cada letra que for sendo
	// criptografada/descriptografada será adicionada nessa String.
	private String saida = "";

	// Configuracao dos plugues
	boolean plugueConfigurado = false;
	private char plugueA;
	private char plugueB;

	private String alfabetoCompleto = "";
	private String conjuntoLetrasInvertida = "";

	private String setandoAlfabetoRefletor= "";
	
	private String valorEntrada;

	// Valor booleano para verificar se está acontecendo uma criptografia ou
	// descriptografia, que a partir de então
	// será utilizado a String do método dentro de cada rotorI, rotorII, rotorIII
	boolean descripta;

	public void configurandoRotor() {
		char letraConfiguracaoUsuarioDigitiou;
		int configurarPlugues;

		System.out.println("ENIGMA REVOLUTION -");
		System.out.println("Desenvolvido por Nathan Felippi\n");
		System.out.println("*TELA CONFIGURAÇÃO DOS ROTORES* (Digitar uma letra de a - Z para cada rotor.)");

		setAlfabetoCompleto("ABCDEFGHIJKLMNOPQRSTUVWYXZ");
		setConjuntoLetrasInvertida(new StringBuilder(getAlfabetoCompleto()).reverse().toString());

		System.out.println("ROTOR 1:");
		letraConfiguracaoUsuarioDigitiou = scanner.next().toUpperCase().charAt(0);
		// adiciona dentro das variaveis char para serem utilizadas no relatório final.
		setValorOriginalRotor1(letraConfiguracaoUsuarioDigitiou);
		// Pega valor convertido e seta dentro do ROTOR I
		setRotor1(conversor(letraConfiguracaoUsuarioDigitiou));

		System.out.println("ROTOR 2:");
		letraConfiguracaoUsuarioDigitiou = scanner.next().toUpperCase().charAt(0);
		// adiciona dentro das variaveis char para serem utilizadas no relatório final.
		setValorOriginalRotor2(letraConfiguracaoUsuarioDigitiou);
		// Pega valor convertido e seta dentro do ROTOR II
		setRotor2(conversor(letraConfiguracaoUsuarioDigitiou));

		System.out.println("ROTOR 3:");
		letraConfiguracaoUsuarioDigitiou = scanner.next().toUpperCase().charAt(0);
		// adiciona dentro das variaveis char para serem utilizadas no relatório final.
		setValorOriginalRotor3(letraConfiguracaoUsuarioDigitiou);
		// Pega valor convertido e seta dentro do ROTOR III
		setRotor3(conversor(letraConfiguracaoUsuarioDigitiou));

		System.out.println("\n");
		System.out.println("Você quer configurar os plugues?");
		System.out.println("Se não configurar ficará o padrão | ABCDEFGHIJKLMNOPQRSTUVWYXZ | e não tem problema!");
		System.out.println("\n1 - Não\n2 - Sim");
		configurarPlugues = scanner.nextInt();

		if (configurarPlugues == 2) {
			plugueConfigurado = true;

			System.out.println("\n");
			System.out.println("*TELA CONFIGURAÇÃO DOS PLUGUES*");
			System.out.println("Você irá trocar letra do alfabeto padrão.");
			System.out.println(
					"Exemplo: Alfabeto (ABCDEFGH..) você ira digitar > A < depois > B < irá trocar o A pelo B, ficando o alfabeto assim (BACDEFGH..)");
			System.out.println("Digite a Letra que voê quer trocar -> ABCDEFGHIJKLMNOPQRSTUVWYXZ <-");
			setPlugueA(scanner.next().toUpperCase().charAt(0));
			System.out.println("Você selecionou o " + getPlugueA() + " trocar pelo : ");
			setPlugueB(scanner.next().toUpperCase().charAt(0));
			System.out.println("Você selecionou o " + getPlugueA() + " que será trocado pelo  " + getPlugueB());

			configuracaoPlugues();
		}

		opcaoUsuarioDesejaRealizar();
	}

	// Converte a letra digitada para um número.
	// Exemplo A B C D E F
	// 0 1 2 3 4 5
	public int conversor(char letra) {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
		int pos = letras.indexOf(letra);
		return pos;
	}

	public void opcaoUsuarioDesejaRealizar() {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		System.out.println("\n");
		System.out.println("Selecione a opção desejada: ");
		System.out.println("1 - Criptografar os dados\n");
		System.out.println("2 - Descriptografar os dados\n");
		opcao = scanner.nextInt();

		if (opcao == 1) {
			descripta = false;
			criptografia();
			relatorioFinal();
		}

		if (opcao == 2) {
			descripta = true;
			descriptografia();
			relatorioFinal();
		}
	}

	public void criptografia() {
		Scanner sc = new Scanner(System.in);
		String entrada;
		char letra;

		System.out.println("\n");
		System.out.println("*TELA CRIPTOGRAFIA*\n");
		System.out.println("Digite palavra: ");
		entrada = sc.nextLine().toUpperCase();
		setValorEntrada(entrada);

		for (int i = 0; i < entrada.length(); i++) {
			letra = rotorI(entrada.charAt(i), getRotor1());
			letra = rotorII(letra, getRotor2());
			letra = rotorIII(letra, getRotor3());
			letra = refletor(letra);
			setSaida(getSaida() + letra);
		}

	}

	public char refletor(char letra) {
		String letras = getAlfabetoCompleto();
		inverterRefletor();
		String letrasInvertidas = getSetandoAlfabetoRefletor();
		int pos = letrasInvertidas.indexOf(letra);

		if (pos > 25) {
			pos = pos - 26;
		}
		return letras.charAt(pos);
	}
	
	public void inverterRefletor() {
		String letras = getAlfabetoCompleto();
		String ladoA = "";
		String ladoB = "";
		String refletorPronto= "";
		char letra;
		
		for (int i = 0; i < letras.length(); i++) {
			letra = letras.charAt(i);
			
			if(i <= 12) {
				ladoA = ladoA + letra;
			}
			
			if(i >= 13) {
				ladoB = ladoB + letra;
			}
		}
		
		refletorPronto = ladoB + ladoA;
		
		setSetandoAlfabetoRefletor(refletorPronto);
	}

	public void descriptografia() {
		Scanner sc = new Scanner(System.in);
		char letra;
		String entrada;

		System.out.println("\n");
		System.out.println("*TELA DESCRIPTOGRAFIA*\n");
		System.out.println("Digite palavra: ");
		entrada = sc.nextLine().toUpperCase();
		setValorEntrada(entrada);

		for (int i = 0; i < entrada.length(); i++) {
			letra = rotorI(entrada.charAt(i), getRotor1());
			letra = rotorII(letra, getRotor2());
			letra = rotorIII(letra, getRotor3());
			letra = refletor(letra);
			setSaida(getSaida() + letra);
		}
	}

	// As letras dentro das condicionais, possui ordem normal e depois espelhadas
	// horizontalmente
	public char rotorI(char letra, int posicaoInicial) {
		String letras;
		if (descripta == false) {
			letras = alfabetoCompleto;
		} else {
			letras = conjuntoLetrasInvertida;
		}
		int pos = letras.indexOf(letra);
		pos = pos + posicaoInicial;
		if (pos > 25) {
			pos = pos - 26;
		}
		return letras.charAt(pos);
	}

	public char rotorII(char letra, int posicaoInicial) {
		String letras;
		if (descripta == false) {
			letras = alfabetoCompleto;
		} else {
			letras = conjuntoLetrasInvertida;
		}
		int pos = letras.indexOf(letra);
		pos = pos + posicaoInicial;
		if (pos > 25) {
			pos = pos - 26;
		}
		return letras.charAt(pos);
	}

	public char rotorIII(char letra, int posicaoInicial) {
		String letras;
		if (descripta == false) {
			letras = alfabetoCompleto;
		} else {
			letras = conjuntoLetrasInvertida;
		}
		int pos = letras.indexOf(letra);
		pos = pos + posicaoInicial;

		if (pos > 25) {
			pos = pos - 26;
		}

		return letras.charAt(pos);
	}

	public void configuracaoPlugues() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
		String alfabeto = "";
		char letra;
		boolean executa;

		String valor1 = Character.toString(getPlugueA());
		String valor2 = Character.toString(getPlugueB());

		for (int i = 0; i < letras.length(); i++) {
			letra = letras.charAt(i);
			executa = false;
			
			if (letra == getPlugueA()) {
				letra = getPlugueB();
				executa = true;
			}

			if (letra == getPlugueB()) {
				if(executa == false) {
					letra = getPlugueA();
				}
			}

			alfabeto = alfabeto + letra;

		}

		setAlfabetoCompleto(alfabeto);
		setConjuntoLetrasInvertida(new StringBuilder(getAlfabetoCompleto()).reverse().toString());
	}

	// Mostra todos os dados que o usuário interagiu na tela.
	public void relatorioFinal() {
		System.out.println("\n");
		System.out.println("### RELATÓRIO FINAL ####");
		System.out.println("Configurações dos Rotores");
		System.out.println("ROTOR I: " + getValorOriginalRotor1());
		System.out.println("ROTOR II: " + getValorOriginalRotor2());
		System.out.println("ROTOR III: " + getValorOriginalRotor3());
		System.out.println("Configurações dos Plugues");
		if (plugueConfigurado == true) {
			System.out.println("Plugue trocado " + getPlugueA() + " > " + getPlugueB() + " Alfabeto | "
					+ getAlfabetoCompleto() + " |");
		} else {
			System.out.println("Plugues não foram trocados ficando Alfabeto normal | ABCDEFGHIJKLMNOPQRSTUVWYXZ |");
		}

		System.out.println("\nPalavra: " + getValorEntrada());
		System.out.println("\nPalavra Criptografada: " + getSaida());
	}

	public int getRotor1() {
		return rotor1;
	}

	public void setRotor1(int rotor1) {
		this.rotor1 = rotor1;
	}

	public int getRotor2() {
		return rotor2;
	}

	public void setRotor2(int rotor2) {
		this.rotor2 = rotor2;
	}

	public int getRotor3() {
		return rotor3;
	}

	public void setRotor3(int rotor3) {
		this.rotor3 = rotor3;
	}

	public String getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public char getValorOriginalRotor1() {
		return valorOriginalRotor1;
	}

	public void setValorOriginalRotor1(char valorOriginalRotor1) {
		this.valorOriginalRotor1 = valorOriginalRotor1;
	}

	public char getValorOriginalRotor2() {
		return valorOriginalRotor2;
	}

	public void setValorOriginalRotor2(char valorOriginalRotor2) {
		this.valorOriginalRotor2 = valorOriginalRotor2;
	}

	public char getValorOriginalRotor3() {
		return valorOriginalRotor3;
	}

	public void setValorOriginalRotor3(char valorOriginalRotor3) {
		this.valorOriginalRotor3 = valorOriginalRotor3;
	}

	public char getPlugueA() {
		return plugueA;
	}

	public void setPlugueA(char plugueA) {
		this.plugueA = plugueA;
	}

	public char getPlugueB() {
		return plugueB;
	}

	public void setPlugueB(char plugueB) {
		this.plugueB = plugueB;
	}

	public String getAlfabetoCompleto() {
		return alfabetoCompleto;
	}

	public void setAlfabetoCompleto(String alfabetoCompleto) {
		this.alfabetoCompleto = alfabetoCompleto;
	}

	public String getConjuntoLetrasInvertida() {
		return conjuntoLetrasInvertida;
	}

	public void setConjuntoLetrasInvertida(String conjuntoLetrasInvertida) {
		this.conjuntoLetrasInvertida = conjuntoLetrasInvertida;
	}

	public String getSetandoAlfabetoRefletor() {
		return setandoAlfabetoRefletor;
	}

	public void setSetandoAlfabetoRefletor(String setandoAlfabetoRefletor) {
		this.setandoAlfabetoRefletor = setandoAlfabetoRefletor;
	}
	
	

}
