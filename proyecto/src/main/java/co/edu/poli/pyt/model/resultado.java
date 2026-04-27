package co.edu.poli.pyt.model;

import java.util.List;

public class Resultado {

    private boolean aciertoTotal;
    private List<String> feedback;
    private String mensaje;

    public Resultado(boolean aciertoTotal, List<String> feedback, String mensaje) {
        this.aciertoTotal = aciertoTotal;
        this.feedback = feedback;
        this.mensaje = mensaje;
    }

    public boolean isAciertoTotal() {
        return aciertoTotal;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public String getMensaje() {
        return mensaje;
    }
}