package ee.taltech.iti0202.computerbuilder.computerFactory;

import ee.taltech.iti0202.computerbuilder.components.*;
import ee.taltech.iti0202.computerbuilder.computer.*;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.ComputerStore;

import java.util.Comparator;

public class ComputerFactory {
    private static ComputerFactory instance = null;
    private final ComputerStore store;
    private final Database database;

    private ComputerFactory(ComputerStore store) {
        this.store = store;
        this.database = Database.getInstance();
    }

    public static ComputerFactory getInstance(ComputerStore store) {
        if (instance == null) {
            instance = new ComputerFactory(store);
        }
        return instance;
    }

    public Computer assembleComputer(double budget, UseCase useCase, Type type) throws ProductNotFoundException, NotEnoughMoneyException {
        try {
            if (type.equals(Type.PC) && useCase.equals(UseCase.GAMING)) {
                return assembleGamingPC(budget);
            } else if (type.equals(Type.PC) && useCase.equals(UseCase.WORKSTATION)) {
                return assembleWorkPC(budget);
            } else if (type.equals(Type.LAPTOP)) {
                return assembleLaptop(budget);
            }
        } catch (IndexOutOfBoundsException e) {
            if (this.database.getComponents().size() == 0) {
                throw new ProductNotFoundException();
            } else throw new NotEnoughMoneyException();
        }
        throw new ProductNotFoundException();
    }

    private Computer assembleLaptop(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * 0.15, 400))
                .withCPU(chooseCPU(budget * 0.1, 200))
                .withGPU(chooseGPU(budget * 0.1, 100))
                .withSSD(chooseSSD(budget * 0.1, 40))
                .withCase(chooseCase(budget * 0.1))
                .withRAM(chooseRAM(budget * 0.1, 40))
                .withMotherBoard(chooseMotherBoard(budget * 0.1, 30))
                .withKeyboard(chooseKeyboard(budget * 0.05, 20))
                .withTouchpad(chooseTouchpad(budget * 0.05, 10))
                .withBattery(chooseBattery(budget * 0.05, 500))
                .withScreen(chooseScreen(budget * 0.05, 10))
                .buildLaptop();
    }

    private Computer assembleWorkPC(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * 0.15, 400))
                .withCPU(chooseCPU(budget * 0.25, 200))
                .withGPU(chooseGPU(budget * 0.1, 100))
                .withSSD(chooseSSD(budget * 0.1, 40))
                .withCase(chooseCase(budget * 0.1))
                .withRAM(chooseRAM(budget * 0.2, 40))
                .withMotherBoard(chooseMotherBoard(budget * 0.1, 30))
                .buildPC();

    }
    private Computer assembleGamingPC(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * 0.15, 400))
                .withCPU(chooseCPU(budget * 0.1, 200))
                .withGPU(chooseGPU(budget * 0.25, 100))
                .withSSD(chooseSSD(budget * 0.1, 40))
                .withCase(chooseCase(budget * 0.1))
                .withRAM(chooseRAM(budget * 0.2, 40))
                .withMotherBoard(chooseMotherBoard(budget * 0.1, 30))
                .buildPC();
    }
    private Computer assembleRegularComputer(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * 0.15, 400))
                .withCPU(chooseCPU(budget * 0.2, 200))
                .withGPU(chooseGPU(budget * 0.15, 100))
                .withSSD(chooseSSD(budget * 0.1, 40))
                .withCase(chooseCase(budget * 0.1))
                .withRAM(chooseRAM(budget * 0.2, 40))
                .withMotherBoard(chooseMotherBoard(budget * 0.1, 30))
                .buildPC();
    }

    private RAM chooseRAM(double budget, int power) {
        RAM ram = (RAM) this.database.getComponents().values().stream()
                .filter(r -> r.getAmount() >= 1 && r.getPrice() <= budget && r.getPowerConsumption() <= power)
                .sorted(Comparator.comparing(Component::getPerformancePoints).reversed()).toList().get(0);
        ram.decreaseAmount(1);
        return ram;
    }

    private Screen chooseScreen(double budget, int power) {
        Screen screen = (Screen) this.database.getComponents().values().stream()
                .filter(s -> s.getAmount() >= 1 && s.getPrice() <= budget && s.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPowerConsumption))
                .toList().get(0);
        screen.decreaseAmount(1);
        return screen;
    }

    private MotherBoard chooseMotherBoard(double budget, int power) {
        MotherBoard motherBoard = (MotherBoard) this.database.getComponents().values().stream()
                .filter(m -> m.getAmount() >= 1 && m.getPrice() <= budget && m.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPerformancePoints).reversed()).toList().get(0);
        motherBoard.decreaseAmount(1);
        return motherBoard;
    }

    private Battery chooseBattery(double budget, int power) {
        Battery battery = (Battery) this.database.getComponents().values().stream()
                .filter(b -> b.getAmount() >= 1 && b.getPrice() <= budget && b.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPowerConsumption))
                .toList().get(0);
        battery.decreaseAmount(1);
        return battery;
    }

    private Touchpad chooseTouchpad(double budget, int power) {
        Touchpad touchpad = (Touchpad) this.database.getComponents().values().stream()
                .filter(t -> t.getAmount() >= 1 && t.getPrice() <= budget && t.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPowerConsumption))
                .toList().get(0);
        touchpad.decreaseAmount(1);
        return touchpad;
    }

    private Keyboard chooseKeyboard(double budget, int power) {
        Keyboard keyboard = (Keyboard) this.database.getComponents().values().stream()
                .filter(k -> k.getAmount() >= 1 && k.getPrice() <= budget && k.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPerformancePoints))
                .toList().get(0);
        keyboard.decreaseAmount(1);
        return keyboard;
    }

    private ComputerCase chooseCase(double budget) {
        ComputerCase computerCase = (ComputerCase) this.database.getComponents().values().stream()
                .filter(c -> c.getAmount() >= 1 && c.getPrice() <= budget)
                .sorted(Comparator.comparing(Component::getPrice).reversed())
                .toList();
        computerCase.decreaseAmount(1);
        return computerCase;
    }

    private SSD chooseSSD(double budget, int power) {
        SSD ssd = (SSD) this.database.getComponents().values().stream()
                .filter(s -> s.getAmount() >= 1 && s.getPrice() <= budget && s.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPerformancePoints).reversed())
                .toList().get(0);
        ssd.decreaseAmount(1);
        return ssd;
    }

    private GPU chooseGPU(double budget, int power) {
        GPU gpu = (GPU) this.database.getComponents().values().stream()
                .filter(g -> g.getAmount() >= 1 && g.getPrice() <= budget && g.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPerformancePoints).reversed())
                .toList().get(0);
        gpu.decreaseAmount(1);
        return gpu;
    }

    private CPU chooseCPU(double budget, int power) {
        CPU cpu = (CPU) this.database.getComponents().values().stream()
                .filter(c -> c.getAmount() >= 1 && c.getPrice() <= budget && c.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPerformancePoints).reversed())
                .toList().get(0);
        cpu.decreaseAmount(1);
        return cpu;
    }

    private PSU choosePSU(double budget, int power) {
        PSU psu = (PSU) this.database.getComponents().values().stream()
                .filter(p -> p.getAmount() >=1 && p.getPrice() <= budget && p.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPowerConsumption).reversed())
                .toList().get(0);
        psu.decreaseAmount(1);
        return psu;
    }
}
