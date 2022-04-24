package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.*;

public class ComputerBuilder {
    private Battery battery;
    private ComputerCase computerCase;
    private CPU cpu;
    private GPU gpu;
    private HDD hdd;
    private Keyboard keyboard;
    private MotherBoard motherBoard;
    private PSU psu;
    private RAM ram;
    private Screen screen;
    private SSD ssd;
    private Touchpad touchpad;

    public ComputerBuilder withBattery(Battery battery) {
        this.battery = battery;
        return this;
    }
    public ComputerBuilder withCase(ComputerCase computerCase) {
        this.computerCase = computerCase;
        return this;
    }
    public ComputerBuilder withCPU(CPU cpu) {
        this.cpu = cpu;
        return this;
    }
    public ComputerBuilder withGPU(GPU gpu) {
        this.gpu = gpu;
        return this;
    }
    public ComputerBuilder withHDD(HDD hdd) {
        this.hdd = hdd;
        return this;
    }
    public ComputerBuilder withKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
        return this;
    }
    public ComputerBuilder withMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
        return this;
    }
    public ComputerBuilder withPSU(PSU psu) {
        this.psu = psu;
        return this;
    }
    public ComputerBuilder withRAM(RAM ram) {
        this.ram = ram;
        return this;
    }
    public ComputerBuilder withScreen(Screen screen) {
        this.screen = screen;
        return this;
    }
    public ComputerBuilder withSSD(SSD ssd) {
        this.ssd = ssd;
        return this;
    }
    public ComputerBuilder withTouchpad(Touchpad touchpad) {
        this.touchpad = touchpad;
        return this;
    }

    public PC buildPC() {
        PC pc = new PC();
        if (this.hdd != null) {
            pc.setHdd(this.hdd);
        } else {
            pc.setSsd(this.ssd);
        }
        pc.setComputerCase(this.computerCase);
        pc.setCpu(this.cpu);
        pc.setGpu(this.gpu);
        pc.setRam(this.ram);
        pc.setMotherBoard(this.motherBoard);
        pc.setPsu(psu);
        return pc;
    }

    public Laptop buildLaptop() {
        Laptop laptop = new Laptop();
        if (this.hdd != null) {
            laptop.setHdd(this.hdd);
        } else {
            laptop.setSsd(this.ssd);
        }
        laptop.setComputerCase(this.computerCase);
        laptop.setCpu(this.cpu);
        laptop.setGpu(this.gpu);
        laptop.setRam(this.ram);
        laptop.setMotherBoard(this.motherBoard);
        laptop.setPsu(psu);
        laptop.setBattery(this.battery);
        laptop.setKeyboard(this.keyboard);
        laptop.setTouchpad(this.touchpad);
        laptop.setScreen(this.screen);
        return laptop;
    }
}
