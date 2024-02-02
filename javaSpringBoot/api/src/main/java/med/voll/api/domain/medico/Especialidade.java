package med.voll.api.domain.medico;

public enum Especialidade {

    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    GINECOLOGIA("ginecologia"),
    DERMATOLOGIA("dermatologia");

    private String especialidadeM;
    Especialidade(String especialidadeM) {
        this.especialidadeM = especialidadeM;
    }
}
