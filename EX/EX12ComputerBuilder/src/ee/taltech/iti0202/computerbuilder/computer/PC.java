package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.CPU;
import ee.taltech.iti0202.computerbuilder.components.ComputerCase;
import ee.taltech.iti0202.computerbuilder.components.GPU;
import ee.taltech.iti0202.computerbuilder.components.HDD;
import ee.taltech.iti0202.computerbuilder.components.MotherBoard;
import ee.taltech.iti0202.computerbuilder.components.PSU;
import ee.taltech.iti0202.computerbuilder.components.RAM;
import ee.taltech.iti0202.computerbuilder.components.SSD;

public class PC extends Computer {

    private final ComputerCase computerCase;
    private final HDD hdd;
    private final SSD ssd;
    private final CPU cpu;
    private final GPU gpu;
    private final RAM ram;
    private final MotherBoard motherBoard;
    private final PSU psu;

    public PC() {
    super();
    this.hdd = null;
    this.ssd = null;
    this.computerCase = null;
    this.cpu = null;
    this.gpu = null;
    this.ram = null;
    this.motherBoard = null;
    this.psu = null;

}

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setComputerCase(ComputerCase computerCase) {
        super.setComputerCase(computerCase);
    }

    @Override
    public void setCpu(CPU cpu) {
        super.setCpu(cpu);
    }

    @Override
    public void setRam(RAM ram) {
        super.setRam(ram);
    }

    @Override
    public void setGpu(GPU gpu) {
        super.setGpu(gpu);
    }

    @Override
    public void setHdd(HDD hdd) {
        super.setHdd(hdd);
    }

    @Override
    public void setMotherBoard(MotherBoard motherBoard) {
        super.setMotherBoard(motherBoard);
    }

    @Override
    public void setPsu(PSU psu) {
        super.setPsu(psu);
    }

    @Override
    public void setSsd(SSD ssd) {
        super.setSsd(ssd);
    }

    @Override
    public int getComputerValue() {
        return super.getComputerValue();
    }
}
