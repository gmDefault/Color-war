package machine_a_glace;

public class Automate {
	int etat_courant;
	int etat_initial;
	int etat_final;
	int transition[][];
	int nb_transitions[];
	Expr tab[];

	public Automate(Node arbre) {
		Automate auto;
		etat_courant = 0;
		etat_initial = 0;
		etat_final = etat_courant;

		if (arbre.Gram == Operateur.Star) {
			auto = new Automate(arbre.FD, etat_courant);
			etat_final = auto.etat_final;

			transition = new int[auto.etat_courant][];
			if (auto.transition != null)
				transition = auto.transition;

			tab = auto.tab;
			nb_transitions = auto.nb_transitions;
			transition[etat_final] = new int[(auto.nb_transitions[etat_final]) + 1];
			transition[etat_final][(auto.nb_transitions[etat_final])] = auto.etat_initial;
			nb_transitions[etat_final]++;
		}

	}

	private Automate(Node arbre, int debut) {

		Automate auto;
		etat_courant = debut;
		etat_initial = debut;
		etat_final = etat_courant;
		if (!arbre.isFeuille()) {
			if (arbre.Gram == Operateur.PointVirgule) {
				auto = new Automate(arbre.FG, etat_courant);
				Automate auto2 = new Automate(arbre.FD, auto.etat_courant);

				etat_final = auto2.etat_final;
				etat_courant = etat_final + 1;

				transition = new int[auto2.etat_courant][];
				tab = new Expr[auto2.etat_courant];
				nb_transitions = new int[auto2.etat_courant];

				if (auto.transition != null) {
					for (int i = 0; i < auto.etat_courant; i++) {
						transition[i] = auto.transition[i];
					}
				}

				for (int i = auto.etat_initial; i < auto.etat_courant; i++) {
					tab[i] = auto.tab[i];
					nb_transitions[i] = auto.nb_transitions[i];
				}

				transition[auto.etat_final] = new int[(nb_transitions[auto.etat_final]) + 1];
				transition[auto.etat_final][(nb_transitions[auto.etat_final])] = auto2.etat_initial;
				nb_transitions[auto.etat_final]++;

				for (int i = auto2.etat_initial; i < auto2.etat_courant; i++) {
					nb_transitions[i] = auto2.nb_transitions[i];
					tab[i] = auto2.tab[i];
					if (auto2.transition != null) {
						transition[i] = new int[nb_transitions[i]];
						for (int j = 0; j < nb_transitions[i]; j++) {

							transition[i][j] = auto2.transition[i][j];
						}
					}

				}

			} else if (arbre.Gram == Operateur.Priorite) {
				auto = new Automate(arbre.FG, etat_courant + 1);
				Automate auto2 = new Automate(arbre.FD, auto.etat_courant);

				etat_final = auto2.etat_final + 1;

				transition = new int[auto2.etat_courant + 1][];
				tab = new Expr[auto2.etat_courant + 1];
				nb_transitions = new int[auto2.etat_courant + 1];

				if (auto.transition != null) {
					for (int i = 0; i < auto.etat_courant; i++) {
						transition[i] = auto.transition[i];
					}
				}

				for (int i = auto.etat_initial; i < auto.etat_courant; i++) {
					tab[i] = auto.tab[i];
					nb_transitions[i] = auto.nb_transitions[i];
				}

				transition[auto.etat_final] = new int[(nb_transitions[auto.etat_final]) + 1];
				transition[auto.etat_final][(nb_transitions[auto.etat_final])] = etat_final;
				nb_transitions[auto.etat_final]++;

				for (int i = auto2.etat_initial; i < auto2.etat_final; i++) {
					nb_transitions[i] = auto2.nb_transitions[i];
					tab[i] = auto2.tab[i];
					if (auto2.transition != null) {
						transition[i] = new int[nb_transitions[i]];
						for (int j = 0; j < nb_transitions[i]; j++) {

							transition[i][j] = auto2.transition[i][j];
						}
					}

				}

				transition[etat_courant] = new int[2];
				transition[etat_courant][0] = auto.etat_initial;
				transition[etat_courant][1] = auto2.etat_initial;

				nb_transitions[etat_courant] = 2;

				nb_transitions[auto2.etat_final] = auto2.nb_transitions[auto2.etat_final] + 1;
				tab[auto2.etat_final] = auto2.tab[auto2.etat_final];
				transition[auto2.etat_final] = new int[nb_transitions[auto2.etat_final]];
				for (int j = 0; j < nb_transitions[auto2.etat_final] - 1; j++) {

					transition[auto2.etat_final][j] = auto2.transition[auto2.etat_final][j];
				}

				transition[auto2.etat_final][nb_transitions[auto2.etat_final] - 1] = etat_final;

				etat_courant = etat_final + 1;

			}

		} else {
			tab = new Expr[etat_courant + 1];
			tab[etat_courant] = arbre.Gram;
			nb_transitions = new int[etat_courant + 1];
			nb_transitions[etat_courant] = 0;
			etat_final = etat_courant;
			etat_courant++;
		}

	}

}
