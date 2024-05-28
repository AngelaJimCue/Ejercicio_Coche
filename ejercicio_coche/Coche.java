package ejercicio_repaso_tercera_evaluacion.ejercicio_coche;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
 
public class Coche {
	private Color color;
	 
	public Coche(Color color) { 
		this.color = color;
	}

	public static Optional<Coche> pintarCoche (Optional<Coche> optCoche, Supplier<Color> supColor) 
			throws NoSuchCocheException,NoValidoColorException{
		
		if (optCoche==null || optCoche.isEmpty()) { 
			throw new NoValidoColorException("No hay ningún coche.");}
		
		Color nuevoColor = supColor.get();
		
		if (optCoche.get().color == nuevoColor) {
			throw new NoValidoColorException("No se puede pintar del mismo color.");}
		
		if (nuevoColor == null) { 
			throw new NoSuchCocheException("No se puede pintar este coche.");}
			
		optCoche.get().color = nuevoColor;
		
		return optCoche;
	}


	@Override
	public String toString() {
		return String.format("--> Color del coche es: %s.", color);
	}
	
	public static void main(String[] args) {
		Coche coche1 = new Coche(Color.AZ);
		System.out.println(coche1.toString());
		
		System.out.println("*************************************************");
		
		try {
			//pintarCoche(Optional.of(new Coche(Color.AM)), ()-> Color.AZ);
			//pintarCoche(Optional.of(new Coche(Color.AM)), ()-> Color.AM);
			pintarCoche(Optional.ofNullable(null), ()-> Color.AM);
		} catch (NoSuchCocheException e) {
			System.out.println(e.getMessage());
		} catch (NoValidoColorException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("*************************************************");
		
		Coche coche2 = new Coche(Color.NE);
		System.out.println(coche2.toString());
		
		Supplier<Color> colorAleatorio = ()->{
			Random random = new Random();
			return Color.values()[random.nextInt(Color.values().length)];
		};
		
		try {
			System.out.printf("Color original:%s.%n", coche2.color);
			pintarCoche(Optional.ofNullable(coche2), colorAleatorio);
			
			System.out.printf("Color cambio:%s.",coche2.color);
			pintarCoche(Optional.ofNullable(coche2), colorAleatorio);
		} catch (NoSuchCocheException e) {
			System.out.println(e.getMessage());
		} catch (NoValidoColorException e) {
			System.out.println(e.getMessage());
		}
	}

	
}
