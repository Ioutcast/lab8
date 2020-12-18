package Worker;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class WorkerPacket implements Serializable {
    LinkedHashSet<Worker> dragons = new LinkedHashSet<Worker>();

    public WorkerPacket() {

    }



}
