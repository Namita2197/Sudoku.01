import java.io.File;

public class Puzzle {

    public static void main(String args[])throws Exception{
        if (args[0].equals("-h")) {
            System.out.println("Type a valid command line argument accordingly:"+"\n"+ "<input file name> ->  reads puzzle from the specified input file and writes the output to the console"+"\n"+
                    "<input file name> <output file name> ->  reads puzzle from the specified input file and writes the output the specify output file");
        }else{

            String resultFile=null;
            if(args.length==2){
                resultFile=args[1];
            }
            Validator validator = new Validator(args[0],resultFile);
            String puzzleArray[][] = validator.getGridValues();
            String gridValues[][] = validator.getGridValues();
            int humanKindCount=0;
            int onePossibleCount=0;
            int backTrackingCount=0;
            validator.validity(gridValues,gridValues[0].length);

                long startTime = System.nanoTime();

                OnePossible onePossible = new OnePossible(gridValues);
                long startTime1 = System.nanoTime();
                onePossible.solve();
                long endTime1 = System.nanoTime();
                long totalTimeTaken1 = endTime1 - startTime1;
                onePossible.consoleDisplay();
                onePossibleCount = onePossible.getOnePossibleCount();
                gridValues = onePossible.getBoard();
                HumanKind humanKind = new HumanKind(gridValues);
                long startTime2 = System.nanoTime();
                humanKind.solve();
                long endTime2 = System.nanoTime();
                long totalTimeTaken2 = endTime2 - startTime2;
                humanKind.consoleDisplay();
                humanKindCount = humanKind.getHumanKindCount();
                gridValues = humanKind.getBoard();
                BackTracking backTracking = new BackTracking(gridValues);
                long startTime3 = System.nanoTime();
                backTracking.solve();
                long endTime3 = System.nanoTime();
                long totalTimeTaken3 = endTime3 - startTime3;
                backTrackingCount = backTracking.getBackTrackingCount();
                gridValues = backTracking.getBoard();
                backTracking.consoleDisplay();
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;

                if (args.length == 2) {
                    File fileOutput = new File(args[1]);
                    validator.writeOutputFile(fileOutput, gridValues, onePossibleCount, humanKindCount, backTrackingCount, totalTime, totalTimeTaken1, totalTimeTaken2, totalTimeTaken3);
                } else {
                    System.out.println(String.valueOf(puzzleArray[0].length));
                    System.out.println();
                    for (int i = 0; i < puzzleArray[0].length + 1; i++) {
                        for (int j = 0; j < puzzleArray[0].length ; j++) {
                            if (puzzleArray[i][j].equals("0")) {
                                System.out.print("-");
                                System.out.print(" ");
                            } else {
                                System.out.print(puzzleArray[i][j]);
                                System.out.print(" ");
                            }
                        }
                        System.out.println();

                    }

                    System.out.println();
                    System.out.println();
                    System.out.print("Solution:");
                    System.out.println();
                    System.out.println();

                    for (int i = 1; i < gridValues[0].length + 1; i++) {
                        for (int j = 0; j < gridValues[0].length; j++) {
                            System.out.print(gridValues[i][j]);
                            System.out.print(" ");
                        }
                        System.out.println();
                    }

                    System.out.println();
                    System.out.println();

                    System.out.print("Total Time:   ");
                    System.out.print(String.valueOf(totalTime)+"   ns");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.print("Strategy               ");
                    System.out.print("Uses          ");
                    System.out.print("Time   ");
                    System.out.println();
                    System.out.print("One Possible Solution  ");
                    System.out.print(onePossibleCount+"      ");
                    System.out.print(String.valueOf(totalTimeTaken1)+"  ns");
                    System.out.println();
                    System.out.print("Human Kind Strategy    ");
                    System.out.print(humanKindCount+"      ");
                    System.out.print(String.valueOf(totalTimeTaken2)+"  ns");
                    System.out.println();
                    System.out.print("BackTracking           ");
                    System.out.print(backTrackingCount+"      ");
                    System.out.print(String.valueOf(totalTimeTaken3)+"  ns");

                }
            }
        }
    }

