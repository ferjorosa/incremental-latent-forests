from experiments.continuous import Exp_100_plants
from experiments.continuous import Exp_Alcohol
from experiments.continuous import Exp_Buddymove
from experiments.continuous import Exp_Geo_music
from experiments.continuous import Exp_Glass
from experiments.continuous import Exp_Ilpd
from experiments.continuous import Exp_Ionosphere
from experiments.continuous import Exp_Iris
from experiments.continuous import Exp_Leaf
from experiments.continuous import Exp_Nba
from experiments.continuous import Exp_Vehicle
from experiments.continuous import Exp_Waveform
from experiments.continuous import Exp_Wdbc
from experiments.continuous import Exp_Wine
from experiments.continuous import Exp_Yeast


def main():
    run = 1
    n_folds = 10
    fold_log = True

    # exp = Exp_Iris.Exp_Iris("iris")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Buddymove.Exp_Buddymove("buddymove")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Yeast.Exp_Yeast("yeast")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Glass.Exp_Glass("glass")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Ilpd.Exp_Ilpd("ilpd")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Alcohol.Exp_Alcohol("alcohol")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Wine.Exp_Wine("wine")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Leaf.Exp_Leaf("leaf")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Nba.Exp_Nba("nba")
    # exp.run(run, n_folds, fold_log)
    #
    # exp = Exp_Vehicle.Exp_Vehicle("vehicle")
    # exp.run(run, n_folds, fold_log)

    exp = Exp_Wdbc.Exp_Wdbc("wdbc")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Ionosphere.Exp_Ionosphere("ionosphere")
    exp.run(run, n_folds, fold_log)

    exp = Exp_100_plants.Exp_100_plants("100_plants")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Waveform.Exp_Waveform("waveform")
    exp.run(run, n_folds, fold_log)

    exp = Exp_Geo_music.Exp_Geo_music("geo_music")
    exp.run(run, n_folds, fold_log)


if __name__ == "__main__":
    main()