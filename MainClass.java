import java.util.Scanner;

public class MainClass { 

    public static void main(String args[]) {
        Scanner _key = new Scanner(System.in);

        double a;
        double b;
        double c;
        double d;
        String x;
        int i;
        String y;

        System.out.println("Programa Teste");
        System.out.println("Digite a");
        a = _key.nextDouble();
        System.out.println("Digite b");
        b = _key.nextDouble();
        System.out.println("Digite c");
        c = _key.nextDouble();
        i = 2+3/4;

        if ((a+b)+2<c) {

            if ((a+1+a)>c) {
                c = 100.0;
                c = 1+a*b/b;
                y = ". Olha um string concatenado!";
                x = "primeiro if"+y;
                System.out.println(x);
            }
            else {
                c = 200.0;
                x = "segundo if";
                System.out.println(x);
            }

        }
        else {
            c = (300.0*3)/((2*10)/a);
            x = "terceiro if";
            System.out.println(x);
        }


        while ((a+b)*3<(100*c)+1) {

            if ((a/2)*(3/4)<(b*c)) {
                System.out.println("O valor de a é :");
                System.out.println(a);
            }

            a = a+1;
        }

        System.out.println("c é igual a ");
        System.out.println(c);
        d = 2;
        System.out.println("d é igual a ");
        System.out.println(d);

    }
}