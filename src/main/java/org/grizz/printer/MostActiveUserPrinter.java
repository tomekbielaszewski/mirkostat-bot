package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.MostActiveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MostActiveUserPrinter implements StatPrinter {
	@Autowired
	private MostActiveUser mostActiveUser;

	@Override
	public void print(Output output) {
		String s = String.format("Najaktywniejszy Mirek to @%s! Łączna liczba wykonanych przez niego akcji* to **%d** \n" +
                "! * - suma z liczby napisanych wpisów i komentarzy jak i liczby podarowanych plusów",
                mostActiveUser.getMostActiveUser(),
                mostActiveUser.getNumberOfActions());
		output.append(s + "\n\n");
	}

}
