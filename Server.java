import java.net.*;
import java.io.*;
import java.sql.*;


class MyServer{

    private static int[][] mat = new int[8][4];

    //cnexiune bd
    public static void DBQuery(){
        String url = "jdbc:mysql://localhost:3306/jdbclicenta";
        String username = "root";
        String password = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            //preluare date din bd si umplere matrice
            ResultSet resultSet = statement.executeQuery("select * from planeplaces");

            while(resultSet.next()) {
                int row, column;
                row = resultSet.getInt(2);
                String x = resultSet.getString(3);
                char c = x.charAt(0);
                if (c == 'A')
                    column = 0;
                else
                    if (c == 'B')
                        column = 1;
                    else
                        if (c == 'C')
                             column = 2;
                        else
                            column = 3;

                mat[row-1][column] = resultSet.getInt(5);
            }
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void updateDB(String linie, String coloana, int greutate){
        String url = "jdbc:mysql://localhost:3306/jdbclicenta";
        String username = "root";
        String password = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            String query = "UPDATE planeplaces SET greutate = " + Integer.toString(greutate) + " WHERE linie = '" + linie + "' AND coloana = '" + coloana+"';";

            int resultSet = statement.executeUpdate(query);
            System.out.println(query);
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

   public static int sum(int matrice[][], int lstart, int cstart, int lfinal, int cfinal)
    {
        int s=0;
        for (int i= lstart; i<= lfinal; i++)
            for(int j= cstart; j<= cfinal; j++)
                s = s + matrice[i][j];
        return s;
    }

    //functie pt generare locuri
    public static String generateTicket(int nr_locuri, int greutate){
        DBQuery();
        String loc="";
        String linieFinala="";
        String coloanaFinala="";
        // verificam daca merge sus sau jos in matrice
        if(sum(mat, 0, 0, 3, 3) <= sum(mat, 4, 0, 7, 3))
        {
            System.out.println("am intrat sus");
            //suntem in partea de sus
            //verificam daca mergem stanga sau dreata
            if(sum(mat, 0, 0, 3, 1) <= sum(mat, 0, 2, 3, 3) )
            {
                //suntem in partea stanga sus
                for(int i= 3; i>=0; i--)
                    for(int j=1; j>= 0; j--)
                        if(mat[i][j] == 0) {
                            mat[i][j] = greutate;
                            int i1 = i +1;
                            char col;
                            if(j == 0)
                                col = 'A';
                            else
                                col = 'B';
                            loc= Integer.toString(i1) + col;
                            linieFinala = Integer.toString(i1);
                            coloanaFinala = Character.toString(col);
                            updateDB(linieFinala, coloanaFinala, greutate);
                            return loc;
                        }
            }
            else{
                //suntem in partea dreapta sus
                for(int i= 3; i>=0; i--)
                    for(int j=2; j<= 3; j++)
                        if(mat[i][j] == 0) {
                            mat[i][j] = greutate;
                            int i1= i+1;
                            char col;
                            if(j == 2)
                                col = 'C';
                            else
                                col = 'D';
                            loc= Integer.toString(i1) + col;
                            linieFinala = Integer.toString(i1);
                            coloanaFinala = Character.toString(col);
                            updateDB(linieFinala, coloanaFinala, greutate);
                            return loc;
                        }
            }
        }
        else{
            //suntem in partea de jos
            //verificam daca mergem stanga sau dreapta
            System.out.println("am intrat jos");
            if(sum(mat, 4, 0, 7, 1) <= sum(mat, 4, 2, 7, 3) )
            {
                System.out.println("am intrat stanga jos");
                //suntem in partea stanga jos
                for(int i= 4; i<=7; i++)
                    for(int j=1; j>= 0; j--)
                        if(mat[i][j] == 0) {
                            mat[i][j] = greutate;
                            int i1= i+1;
                            char col;
                            if(j == 0)
                                col = 'A';
                            else
                                col = 'B';
                            loc= Integer.toString(i1) + col;
                            linieFinala = Integer.toString(i1);
                            coloanaFinala = Character.toString(col);
                            updateDB(linieFinala, coloanaFinala, greutate);
                            return loc;
                        }
            }
            else{
                //suntem in partea dreapta jos
                System.out.println("am intrat dreapta jos");
                for(int i=4 ; i<=7; i++)
                    for(int j=2; j<= 3; j++)
                        if(mat[i][j] == 0) {
                            mat[i][j] = greutate;
                            int i1 = i+1;
                            char col;
                            if(j == 2)
                                col='C';
                            else
                                col = 'D';
                            loc= Integer.toString(i1) + col;
                            linieFinala = Integer.toString(i1);
                            coloanaFinala = Character.toString(col);
                            updateDB(linieFinala, coloanaFinala, greutate);
                            return loc;
                        }
            }

        }
        updateDB(linieFinala, coloanaFinala, greutate);
        return loc;
    }

    //prelucrare date de la server
    public static String handleClientRequest(String request){
        int nr_locuri, greutate;
        //apelez db request pt a umple matricea de date

        String[] numere = request.split(" ");
        nr_locuri = Integer.parseInt(numere[0]);
        greutate = Integer.parseInt(numere[1]);
        System.out.println("nr locuri: " + nr_locuri);
        System.out.println("greutate: " + greutate);
        return generateTicket(nr_locuri, greutate);
    }

    //conexiune la client
    public static void main(String args[])throws Exception{
        ServerSocket ss=new ServerSocket(3333);
        Socket s=ss.accept();

        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str="";
        String response= "";

        str=din.readUTF();
        System.out.println("client says: "+str);
        response = handleClientRequest(str);

        dout.writeUTF(response);
        dout.flush();

        din.close();
        s.close();
        ss.close();
    }}