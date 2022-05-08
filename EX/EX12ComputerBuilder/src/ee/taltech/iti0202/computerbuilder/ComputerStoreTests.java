package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.Battery;
import ee.taltech.iti0202.computerbuilder.components.CPU;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.components.ComputerCase;
import ee.taltech.iti0202.computerbuilder.components.GPU;
import ee.taltech.iti0202.computerbuilder.components.HDD;
import ee.taltech.iti0202.computerbuilder.components.Keyboard;
import ee.taltech.iti0202.computerbuilder.components.MotherBoard;
import ee.taltech.iti0202.computerbuilder.components.PSU;
import ee.taltech.iti0202.computerbuilder.components.RAM;
import ee.taltech.iti0202.computerbuilder.components.SSD;
import ee.taltech.iti0202.computerbuilder.components.Screen;
import ee.taltech.iti0202.computerbuilder.components.Touchpad;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Type;
import ee.taltech.iti0202.computerbuilder.computer.UseCase;
import ee.taltech.iti0202.computerbuilder.computerFactory.ComputerFactory;
import ee.taltech.iti0202.computerbuilder.customer.Customer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.ComputerStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ComputerStoreTests {
    public final static int TEN = 10;
    public final static int TWELVE = 12;
    public final static int FIFTY = 50;
    public final static int THIRTY = 30;
    public final static int FIVEHUNDRED = 500;
    public final static int TWENTY = 20;
    public final static int FORTY = 40;
    public final static int HUNDRED = 100;
    public final static int TWOHUNDRED = 200;
    public final static int THREEHUNDRED = 300;
    public final static int HUNDREDFIFTY = 150;
    public final static int SEVENTY = 70;
    public final static int SIXTY = 60;
    public final static int TWOHUNDREDFIFTY = 250;
    public final static int THREETHOUSAND = 3000;
    public final static int FOURTHOUSAND = 4000;
    public final static int INVENTORYVALUE = 2720;

    Battery battery = new Battery("b1", Component.Type.BATTERY, FIFTY, "m1", THIRTY, FIVEHUNDRED);
    ComputerCase computerCase = new ComputerCase("case1", Component.Type.CASE, TWENTY, "m2", TWENTY, FIVEHUNDRED);
    CPU cpu = new CPU("cpu1", Component.Type.CPU, HUNDRED, "m3", FIFTY, HUNDRED);
    GPU gpu = new GPU("gpu1", Component.Type.GPU, HUNDRED, "m4", THIRTY, FIFTY);
    HDD hdd = new HDD("hdd1", Component.Type.HDD, HUNDREDFIFTY, "m5", FORTY, THREEHUNDRED);
    Keyboard keyboard = new Keyboard("keyboard", Component.Type.KEYBOARD, HUNDRED, "m6", FIFTY, TEN);
    MotherBoard motherBoard = new MotherBoard("motherBoard", Component.Type.MOTHERBOARD, TWOHUNDRED, "m7", SEVENTY, THIRTY);
    PSU psu = new PSU("psu", Component.Type.PSU, TWOHUNDREDFIFTY, "m8", FIFTY, FIVEHUNDRED);
    RAM ram = new RAM("ram", Component.Type.RAM, SEVENTY, "m9", SIXTY, THIRTY);
    Screen screen = new Screen("screen", Component.Type.SCREEN, FIFTY, "m10", FIFTY, THREEHUNDRED);
    SSD ssd = new SSD("ssd", Component.Type.SSD, HUNDRED, "m11", FORTY, FORTY);
    Touchpad touchpad = new Touchpad("touchpad", Component.Type.TOUCHPAD, SEVENTY, "m12", FIFTY, THREEHUNDRED);

    Database database1 = new Database();

    @Test
    void testComponentConstructor() {
        Assertions.assertEquals(battery.getId(), 0);
        Assertions.assertEquals(computerCase.getId(), 1);
        Assertions.assertEquals(computerCase.getName(), "case1");
        Assertions.assertEquals(cpu.getType(), Component.Type.CPU);
        Assertions.assertEquals(gpu.getPrice(), HUNDRED);
        Assertions.assertEquals(hdd.getManufacturer(), "m5");
        Assertions.assertEquals(keyboard.getPerformancePoints(), FIFTY);
        Assertions.assertEquals(motherBoard.getPowerConsumption(), THIRTY);

    }
    @Test
    void testAssembleWorkPC() throws ProductAlreadyExistsException, NotEnoughMoneyException, ProductNotFoundException {

        ComputerStore computerStore1 = new ComputerStore("store1", BigDecimal.valueOf(FIVEHUNDRED), BigDecimal.valueOf(2));

        database1.saveComponent(battery);
        database1.saveComponent(computerCase);
        database1.saveComponent(cpu);
        database1.saveComponent(gpu);
        database1.saveComponent(hdd);
        database1.saveComponent(keyboard);
        database1.saveComponent(motherBoard);
        database1.saveComponent(psu);
        database1.saveComponent(ram);
        database1.saveComponent(screen);
        database1.saveComponent(ssd);
        database1.saveComponent(touchpad);

        computerStore1.setDatabase(database1);

        Assertions.assertEquals(computerStore1.getBalance(), BigDecimal.valueOf(FIVEHUNDRED));

        ComputerFactory computerFactory = new ComputerFactory(computerStore1);

        computerStore1.setComputerFactory(computerFactory);

        Customer customer1 = new Customer("Kati", BigDecimal.valueOf(THREETHOUSAND));

        Computer pc1 = computerStore1.purchaseComputer(customer1, UseCase.WORKSTATION, Type.PC);


        List<Computer> computers = List.of(pc1);

        Assertions.assertEquals(customer1.getComputers(), computers);
    }

    @Test
    void testAssembleWorkLaptop() throws ProductAlreadyExistsException,
            NotEnoughMoneyException, ProductNotFoundException {

        ComputerStore computerStore2 = new ComputerStore("store2", BigDecimal.valueOf(FIVEHUNDRED), BigDecimal.valueOf(2));

        database1.saveComponent(battery);
        database1.saveComponent(computerCase);
        database1.saveComponent(cpu);
        database1.saveComponent(gpu);
        database1.saveComponent(hdd);
        database1.saveComponent(keyboard);
        database1.saveComponent(motherBoard);
        database1.saveComponent(psu);
        database1.saveComponent(ram);
        database1.saveComponent(screen);
        database1.saveComponent(ssd);
        database1.saveComponent(touchpad);

        computerStore2.setDatabase(database1);

        ComputerFactory computerFactory = new ComputerFactory(computerStore2);

        computerStore2.setComputerFactory(computerFactory);

        Customer customer2 = new Customer("Mari", BigDecimal.valueOf(THREETHOUSAND));

        Computer laptop1 = computerStore2.purchaseComputer(customer2, UseCase.WORKSTATION, Type.LAPTOP);

        List<Computer> laptops = List.of(laptop1);


        Assertions.assertEquals(customer2.getComputers(), laptops);
    }

    @Test
    void testAssembleGamingPC() throws ProductAlreadyExistsException, NotEnoughMoneyException, ProductNotFoundException {

        ComputerStore computerStore3 = new ComputerStore("store3", BigDecimal.valueOf(FIVEHUNDRED), BigDecimal.valueOf(2));

        database1.saveComponent(battery);
        database1.saveComponent(computerCase);
        database1.saveComponent(cpu);
        database1.saveComponent(gpu);
        database1.saveComponent(hdd);
        database1.saveComponent(keyboard);
        database1.saveComponent(motherBoard);
        database1.saveComponent(psu);
        database1.saveComponent(ram);
        database1.saveComponent(screen);
        database1.saveComponent(ssd);
        database1.saveComponent(touchpad);

        computerStore3.setDatabase(database1);

        ComputerFactory computerFactory = new ComputerFactory(computerStore3);

        computerStore3.setComputerFactory(computerFactory);

        Customer customer3 = new Customer("Mati", BigDecimal.valueOf(FOURTHOUSAND));

        Computer gamingComputer = computerStore3.purchaseComputer(customer3, UseCase.GAMING, Type.PC);

        List<Computer> gamingComputers = List.of(gamingComputer);

        Assertions.assertEquals(customer3.getComputers(), gamingComputers);
    }

    @Test
    void testPurchaseComponent() throws ProductAlreadyExistsException, NotEnoughMoneyException, ProductNotFoundException, OutOfStockException {

        ComputerStore computerStore4 = new ComputerStore("store4", BigDecimal.valueOf(FIVEHUNDRED), BigDecimal.valueOf(2));

        database1.saveComponent(battery);
        database1.saveComponent(computerCase);
        database1.saveComponent(cpu);
        database1.saveComponent(gpu);
        database1.saveComponent(hdd);
        database1.saveComponent(keyboard);
        database1.saveComponent(motherBoard);
        database1.saveComponent(psu);
        database1.saveComponent(ram);
        database1.saveComponent(screen);
        database1.saveComponent(ssd);
        database1.saveComponent(touchpad);

        computerStore4.setDatabase(database1);

        Assertions.assertEquals(computerStore4.getBalance(), BigDecimal.valueOf(FIVEHUNDRED));

        ComputerFactory computerFactory = new ComputerFactory(computerStore4);

        computerStore4.setComputerFactory(computerFactory);

        Customer customer4 = new Customer("Kati", BigDecimal.valueOf(THREETHOUSAND));

        Component component1 = computerStore4.purchaseComponent(battery.getId(), customer4);


        List<Component> components = List.of(component1);

        Assertions.assertEquals(customer4.getComponents(), components);
    }

    @Test
    void testSortingStoresDatabase() throws ProductAlreadyExistsException, ProductNotFoundException {
        Database database2 = new Database();

        ComputerStore computerStore3 = new ComputerStore("store3", BigDecimal.valueOf(FIVEHUNDRED), BigDecimal.valueOf(2));

        database2.saveComponent(battery);
        database2.saveComponent(computerCase);
        database2.saveComponent(cpu);
        database2.saveComponent(gpu);
        database2.saveComponent(hdd);
        database2.saveComponent(keyboard);
        database2.saveComponent(motherBoard);
        database2.saveComponent(psu);
        database2.saveComponent(ram);
        database2.saveComponent(screen);
        database2.saveComponent(ssd);
        database2.saveComponent(touchpad);

        database2.increaseComponentStock(battery.getId(), 2);

        computerStore3.setDatabase(database2);

        Assertions.assertEquals(computerStore3.getAvailableComponents().size(), TWELVE);

        Assertions.assertEquals(computerStore3.getComponentsSortedByAmount().get(0), battery);

        Assertions.assertEquals(computerStore3.getComponentsSortedByName().get(0), battery);

        Assertions.assertEquals(computerStore3.getComponentsSortedByPrice().get(0), psu);

        Assertions.assertEquals(computerStore3.filterByType(Component.Type.BATTERY).size(), 1);

        Assertions.assertEquals(computerStore3.getInventoryValue().intValue(), INVENTORYVALUE);
    }

}
