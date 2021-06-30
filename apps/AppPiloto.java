package apps;

import java.io.IOException;
import java.util.Scanner;

import classes.Piloto;

public class AppPiloto {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 5; //inicialmente vai começar com 5 cadastros, por 'padrão'
        int opcao, qtdCadastrados = 0;
        Piloto[] pilotos = new Piloto[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);


        do {
            limparTela();
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos. Retorne ao Menu e digite a opção '4' para aumentar o armazenamento.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui

                Piloto newPiloto = new Piloto();

                System.out.print("Digite o nome do piloto: ");
                newPiloto.setNome(in.nextLine());

                System.out.print("Agora, digite o CPF: ");
                newPiloto.setCpf(in.nextLine());
                    
                System.out.print("Tipo de Aeronave, sendo: \n1 - Pessoal \n2 - Privado / Profissional \nOpção: ");
                int tipoaero = in.nextInt();
                if (tipoaero == 1) {
                    newPiloto.setAeronave("Aeronave de uso Pessoal");
                } else newPiloto.setAeronave("Aeronave de uso Privado / Profissional");
                

                //armazenar dados\\
                pilotos[qtdCadastrados] = newPiloto;
                qtdCadastrados++;
                //armazenar dados\\



                System.out.println("\nPiloto cadastrado com sucesso!");
                voltarMenu(in);
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir. Retorne ao Menu e digite a opção '1'.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui

                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.println("Nome do Piloto: " + pilotos[i].getNome());
                    System.out.println("CPF: " + pilotos[i].getCpf());
                    System.out.println("Modalidade da Aeronave: " + pilotos[i].getAeronave());

                }

                voltarMenu(in);


            } else if (opcao == 3) {
                System.out.print("Insira o CPF do Piloto: ");
                String cpf = in.nextLine();
                boolean certo = false;

                for (int i = 0; i < qtdCadastrados && !certo; i++) {

                    if (cpf.equals(pilotos[i].getCpf())) {
                        certo = true;
                        System.out.print("Piloto encontrado na base: " + pilotos[i].getNome() + "\nCPF: " + pilotos[i].getCpf());
                    }
                }
                if (!certo) {
                    System.out.println("Não há nenhum Piloto com o CPF informado! Por favor, retorne ao menu e tente novamente.");
                }
                voltarMenu(in);



            } else if (opcao == 4) {
                System.out.print("Por padrão, o sistema permite até 5 cadastros na base. Deseja realmente alterar a quantidade máxima? Digite 1 para sim, 2 para retornar ao Menu. ");
                int opc = in.nextInt();
                if (opc == 1) {
                    System.out.print("Digite o tamanho do novo armazenamento: ");
                    int tamanhoVt = in.nextInt();
                    Piloto[] aux = pilotos;
                    pilotos = new Piloto[tamanhoVt];
                    MAX_ELEMENTOS = tamanhoVt;
    
                    for (int i = 0; i < qtdCadastrados; i++) {
                        pilotos[i] = aux[i];
                    }
                    System.out.println("Espaço de armazenamento alterado com sucesso!");
                    voltarMenu(in);
                
            } else voltarMenu(in); }
                
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.print("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
public static void limparTela() {
    try {
        // Limpa toda a tela do console
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
    } catch(Exception e) {}
}

}