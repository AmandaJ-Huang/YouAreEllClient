package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.codemodel.internal.JAssignmentTarget;
import controllers.IdController;
import controllers.JsonController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll urll = new YouAreEll(new MessageController(), new IdController());
        TransactionController teeCtrlr = new TransactionController(new MessageController(), new IdController());
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // id
                // returns all available IDs with command ids
                if (list.contains("ids") && list.size() == 1) {
                    String results = teeCtrlr
                            .getIds()
                            .stream()
                            .map(Id::toString)
                            .collect(Collectors.joining());
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                // posts Name and GitHubId to server
                if (list.contains("ids") && list.size() == 3) {
                    String results = teeCtrlr
                            .postId(list.get(1), list.get(2));
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                // changes Name for existing GitHubId in server
                if (list.contains("ids") && list.size() == 2) {

                    continue;
                }

                // message
                // returns all messages from my githubName
                if (list.contains("messages") && list.size() == 1) {
                    String results = teeCtrlr
                            .getAllMessages()
                            .stream()
                            .map(Message::toString)
                            .collect(Collectors.joining());
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                // posts message
                if (list.contains("send") && list.size() >= 3) {
                    int endingIndex = list.size();
                    int indexOfTo = (list.size()-2);
                    if (list.get(indexOfTo).equals("to")) {
                        String myName = list.get(1);
                        String friendName = list.get(list.size()-1);
                        String message = "" + list.subList(2, indexOfTo);
                        String results = teeCtrlr
                                .postMessageToFriend(myName, friendName, message)
                                .toString();
                        SimpleShell.prettyPrint(results);
                    } else {
                        String message = "" + list.subList(2, endingIndex);
                        String myName = list.get(1);
                        String results = teeCtrlr
                                .postMessage(myName, message)
                                .toString();
                        SimpleShell.prettyPrint(results);
                    }
                    continue;
                }

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // // wait, wait, what curiousness is this?
                // Process process = pb.start();

                // //obtain the input stream
                // InputStream is = process.getInputStream();
                // InputStreamReader isr = new InputStreamReader(is);
                // BufferedReader br = new BufferedReader(isr);

                // //read output of the process
                // String line;
                // while ((line = br.readLine()) != null)
                //     System.out.println(line);
                // br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }
    }

}