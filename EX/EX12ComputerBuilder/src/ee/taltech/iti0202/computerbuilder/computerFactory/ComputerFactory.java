package ee.taltech.iti0202.computerbuilder.computerFactory;

import ee.taltech.iti0202.computerbuilder.components.Battery;
import ee.taltech.iti0202.computerbuilder.components.CPU;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.components.ComputerCase;
import ee.taltech.iti0202.computerbuilder.components.GPU;
import ee.taltech.iti0202.computerbuilder.components.Keyboard;
import ee.taltech.iti0202.computerbuilder.components.MotherBoard;
import ee.taltech.iti0202.computerbuilder.components.PSU;
import ee.taltech.iti0202.computerbuilder.components.RAM;
import ee.taltech.iti0202.computerbuilder.components.SSD;
import ee.taltech.iti0202.computerbuilder.components.Screen;
import ee.taltech.iti0202.computerbuilder.components.Touchpad;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.ComputerBuilder;
import ee.taltech.iti0202.computerbuilder.computer.Type;
import ee.taltech.iti0202.computerbuilder.computer.UseCase;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.ComputerStore;

import java.util.Comparator;

public final class ComputerFactory {
    public static final double QUARTER = 0.15;
    public static final double TENPERCENT = 0.1;
    public static final double FIVEPERCENT = 0.05;
    public static final double FIFTEENPERCENT = 0.15;
    public static final double TWENTYFIFEPERCENT = 0.25;
    public static final double TWENTYPERCENT = 0.2;
    public static final int FOURHUNDRED = 400;
    public static final int FIVEHUNDRED = 500;
    public static final int HUNDRED = 100;
    public static final int TWENTY = 20;
    public static final int TEN = 10;
    public static final int FORTY = 40;
    public static final int TWOHUNDRED = 200;
    public static final int THIRTY = 30;

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

    public Computer assembleComputer(double budget, UseCase useCase, Type type)
            throws ProductNotFoundException, NotEnoughMoneyException {
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
                .withPSU(choosePSU(budget * QUARTER, FOURHUNDRED))
                .withCPU(chooseCPU(budget * TENPERCENT, TWOHUNDRED))
                .withGPU(chooseGPU(budget * TENPERCENT, HUNDRED))
                .withSSD(chooseSSD(budget * TENPERCENT, FORTY))
                .withCase(chooseCase(budget * TENPERCENT))
                .withRAM(chooseRAM(budget * TENPERCENT, FORTY))
                .withMotherBoard(chooseMotherBoard(budget * TENPERCENT, THIRTY))
                .withKeyboard(chooseKeyboard(budget * FIVEPERCENT, TWENTY))
                .withTouchpad(chooseTouchpad(budget * FIVEPERCENT, TEN))
                .withBattery(chooseBattery(budget * FIVEPERCENT, FIVEHUNDRED))
                .withScreen(chooseScreen(budget * FIVEPERCENT, TEN))
                .buildLaptop();
    }

    private Computer assembleWorkPC(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * FIFTEENPERCENT, FOURHUNDRED))
                .withCPU(chooseCPU(budget * TWENTYFIFEPERCENT, TWOHUNDRED))
                .withGPU(chooseGPU(budget * TENPERCENT, HUNDRED))
                .withSSD(chooseSSD(budget * TENPERCENT, FORTY))
                .withCase(chooseCase(budget * TENPERCENT))
                .withRAM(chooseRAM(budget * TWENTYPERCENT, FORTY))
                .withMotherBoard(chooseMotherBoard(budget * TENPERCENT, THIRTY))
                .buildPC();

    }
    private Computer assembleGamingPC(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * FIFTEENPERCENT, FOURHUNDRED))
                .withCPU(chooseCPU(budget * TENPERCENT, TWOHUNDRED))
                .withGPU(chooseGPU(budget * TWENTYFIFEPERCENT, HUNDRED))
                .withSSD(chooseSSD(budget * TENPERCENT, FORTY))
                .withCase(chooseCase(budget * TENPERCENT))
                .withRAM(chooseRAM(budget * TWENTYPERCENT, FORTY))
                .withMotherBoard(chooseMotherBoard(budget * TENPERCENT, THIRTY))
                .buildPC();
    }
    private Computer assembleRegularComputer(double budget) {
        return new ComputerBuilder()
                .withPSU(choosePSU(budget * FIFTEENPERCENT, FOURHUNDRED))
                .withCPU(chooseCPU(budget * TWENTYPERCENT, TWOHUNDRED))
                .withGPU(chooseGPU(budget * FIFTEENPERCENT, HUNDRED))
                .withSSD(chooseSSD(budget * TENPERCENT, FORTY))
                .withCase(chooseCase(budget * TENPERCENT))
                .withRAM(chooseRAM(budget * TWENTYPERCENT, FORTY))
                .withMotherBoard(chooseMotherBoard(budget * TENPERCENT, THIRTY))
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
                .filter(p -> p.getAmount() >= 1 && p.getPrice() <= budget && p.getPowerConsumption() >= power)
                .sorted(Comparator.comparing(Component::getPowerConsumption).reversed())
                .toList().get(0);
        psu.decreaseAmount(1);
        return psu;
    }
}
