package animales;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class Animal {

    protected String codigo;
    private LocalDate fechaNacimiento;
    private char sexo;
    private double peso;

    public Animal(String codigo, String fechaNacimiento, char sexo, double peso) throws IllegalArgumentException {

        if (esCodigoValido(codigo) && (esSexoValido(sexo)) && (peso > 0)) {
            this.codigo = codigo;
            this.sexo = sexo;
            this.peso = peso;
            this.fechaNacimiento = generarFecha(fechaNacimiento);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Animal(Animal otroAnimal) {
        this.codigo = otroAnimal.codigo;
        this.fechaNacimiento = otroAnimal.fechaNacimiento;
        this.sexo = otroAnimal.sexo;
        this.peso = otroAnimal.peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public char getSexo() {
        return sexo;
    }

    public double getPeso() {
        return peso;
    }

    public String getFechaNacimiento() throws IllegalArgumentException {
        String fecha;
        if (this.fechaNacimiento == null) {
            fecha = null;
        } else {
            fecha = getFechaNacimiento('-');
        }
        return fecha;
    }

    public String getFechaNacimiento(char separador) {
        String fecha = null;

        if (separador != '-' && separador != '/') {
            throw new IllegalArgumentException();
        } else {
            if (this.fechaNacimiento != null) {
                fecha = String.format("%02d%c%02d%c%04d", this.fechaNacimiento.getDayOfMonth(), separador, this.fechaNacimiento.getMonthValue(), separador, this.fechaNacimiento.getYear());
            }
        }

        return fecha;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = generarFecha(fechaNacimiento);
    }

    public void setCodigo(String codigo) {
        if (esCodigoValido(codigo)) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setSexo(char sexo) {
        if (esSexoValido(sexo)) {
            this.sexo = sexo;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean esCodigoValido(String codigo) {
        return codigo.matches("[a-z0-9]{5}");
    }

    private boolean esSexoValido(char sexo) {
        return ('H' == sexo || 'M' == sexo);
    }

    private LocalDate generarFecha(String fecha) {
        LocalDate fechaCorrecta = null;
        int dia;
        int mes;
        int anyo;

        if (!fecha.matches("[0-9]{2}[-][0-9]{2}[-][0-9]{4}") && !fecha.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}")) {
            throw new IllegalArgumentException();
        } else {

            dia = Integer.parseInt(fecha.substring(0, 2));
            mes = Integer.parseInt(fecha.substring(3, 5));
            anyo = Integer.parseInt(fecha.substring(6, fecha.length()));

            try {
                fechaCorrecta = LocalDate.of(anyo, mes, dia);
            } catch (DateTimeException ex) {
                throw new IllegalArgumentException();
            }
        }

        return fechaCorrecta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.codigo);
        hash = 29 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 29 * hash + this.sexo;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (this.sexo != other.sexo) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return Objects.equals(this.fechaNacimiento, other.fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Animal{" + "codigo=" + codigo + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", peso=" + peso + '}';
    }
    

}
