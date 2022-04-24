package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.CPU;
import ee.taltech.iti0202.computerbuilder.components.ComputerCase;
import ee.taltech.iti0202.computerbuilder.components.GPU;
import ee.taltech.iti0202.computerbuilder.components.HDD;
import ee.taltech.iti0202.computerbuilder.components.MotherBoard;
import ee.taltech.iti0202.computerbuilder.components.PSU;
import ee.taltech.iti0202.computerbuilder.components.RAM;
import ee.taltech.iti0202.computerbuilder.components.SSD;


public class Computer {
    private ComputerCase computerCase;
    private CPU cpu;
    private GPU gpu;
    private HDD hdd;
    private MotherBoard motherBoard;
    private PSU psu;
    private RAM ram;
    private SSD ssd;
    private int id;
    private static int idCount = 0;

    public Computer() {
        this.id = idCount;
        this.computerCase = null;
        this.cpu = null;
        this.gpu = null;
        this.hdd = null;
        //this.keyboard = null;
        this.motherBoard = null;
        //this.powerSupply = null;
        this.ram = null;
        //this.screen = null;
        this.ssd = null;
        //this.touchpad = null;
        idCount++;
    }

    public int getId() {
        return id;
    }

    public ComputerCase getComputerCase() {
        return computerCase;
    }

    public CPU getCpu() {
        return cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public HDD getHdd() {
        return hdd;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public PSU getPsu() {
        return psu;
    }

    public RAM getRam() {
        return ram;
    }

    public SSD getSsd() {
        return ssd;
    }

    public void setComputerCase(ComputerCase computerCase) {
        this.computerCase = computerCase;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public void setPsu(PSU psu) {
        this.psu = psu;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public void setSsd(SSD ssd) {
        this.ssd = ssd;
    }

    public int getComputerValue() {
        return computerCase.getPrice() + cpu.getPrice() + gpu.getPrice()
                + hdd.getPrice() + motherBoard.getPrice() + ram.getPrice() + ssd.getPrice();
    }
}
