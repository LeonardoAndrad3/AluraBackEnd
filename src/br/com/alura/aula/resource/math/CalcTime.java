package br.com.alura.aula.resource.math;

import br.com.alura.aula.resource.Film;
import br.com.alura.aula.resource.Title;

public class CalcTime {

    private int totalTime;

    public int getTotalTime() {
        return totalTime;
    }

    public void includ(Title t){
        totalTime += t.getDurationInMinutes();
    }
}
