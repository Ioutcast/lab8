package Worker;


import static Worker.Status.*;


/**
 * Цветной интерфейс.
 * Меняет цвет имени дракона на тот,
 * что установлен у него в поле Color
 *
 *
 */



public interface ColorText {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

        static String Text(String string, Status status){
        String setcolor = "";


        if(status == FIRED){
            setcolor = ANSI_RED;
        }
        if(status == FIRED){
            setcolor = ANSI_GREEN;
        }
        if(status == FIRED){
            setcolor = ANSI_YELLOW;
        }if(status == HIRED){
            setcolor = ANSI_BLUE;
        }if(status == HIRED){
            setcolor = ANSI_PURPLE;
        }
        if(status == RECOMMENDED_FOR_PROMOTION){
            setcolor = ANSI_CYAN;
        }
        if(RECOMMENDED_FOR_PROMOTION == status){
            setcolor = ANSI_WHITE;
        }
        return setcolor + string + ANSI_RESET;
    }
}
