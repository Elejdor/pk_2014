import javax.swing.JOptionPane;

public class kalkulator {

	public static void main(String[] args) {
		String text1 = JOptionPane.showInputDialog("Podaj piewsz¹ liczbê:");
		String text2 = JOptionPane.showInputDialog("Podaj drug¹ liczbê:");
		Double liczba1 = Double.valueOf(text1);
		Double liczba2 = Double.valueOf(text2);
		String text3 = JOptionPane.showInputDialog("Podaj znak dzia³ania:");
		//JOptionPane.showMessageDialog(null,text3.hashCode());
		Double tmp = 0.0;
		switch(text3.hashCode())
		{
		case 43: // +
			tmp = liczba1 + liczba2;
			JOptionPane.showMessageDialog(null,tmp);
			break;
		case 45: // -
			tmp = liczba1 - liczba2;
			JOptionPane.showMessageDialog(null,tmp);
			break;
		case 42: // *
			JOptionPane.showMessageDialog(null,tmp);
			break;
		case 47: // /
			if(liczba2 != 0)
			{
				tmp = liczba1 / liczba2;
				JOptionPane.showMessageDialog(null,tmp);
			}else
			{
				JOptionPane.showMessageDialog(null,"Nie mo¿esz dzieliæ przez 0!");
			}
			break;
			default:
				JOptionPane.showMessageDialog(null,"Podano niew³aœciwy znak dzia³ania!!!");
		}
	}
}
