package tpfinalpoo2;

public class ConstructorHtml {

    private final StringBuilder HTML = new StringBuilder();
    private int nivelIndentacion = 0;
    private final String INDENTACION = "    ";

    private void agregarIndentacion() {
    	HTML.append(INDENTACION.repeat(nivelIndentacion));
    }

    public ConstructorHtml abrir(String etiqueta) {
        agregarIndentacion();
        HTML.append("<").append(etiqueta).append(">\n");
        nivelIndentacion++;
        return this;
    }

    public ConstructorHtml cerrar(String etiqueta) {
        nivelIndentacion = Math.max(0, nivelIndentacion - 1);
        agregarIndentacion();
        HTML.append("</").append(etiqueta).append(">\n");
        return this;
    }

    public ConstructorHtml elemento(String etiqueta, String contenido) {
        agregarIndentacion();
        HTML.append("<").append(etiqueta).append(">")
            .append(contenido)
            .append("</").append(etiqueta).append(">\n");
        return this;
    }

    public String construir() {
        return HTML.toString();
    }
}
