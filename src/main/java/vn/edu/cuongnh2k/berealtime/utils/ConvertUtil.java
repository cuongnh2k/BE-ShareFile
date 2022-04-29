package vn.edu.cuongnh2k.berealtime.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ConvertUtil {

    public static Pageable buildPageable(Integer page, Integer size, String orderString) {
        Sort sort = Sort.by(buildOrderList(orderString));
        return PageRequest.of(page, size, sort);
    }

    public static List<Sort.Order> buildOrderList(String orderString) {
        List<Sort.Order> orders = new LinkedList<>();
        if (StringUtils.isBlank(orderString)) {
            orders.add(Sort.Order.desc("id"));
        } else {
            orderString = StringUtils.deleteWhitespace(orderString);
            if (orderString.contains(",")) {
                String[] orderArray = orderString.split(",");
                for (String orderChildString : orderArray) {
                    appendOrderList(orders, orderChildString);
                }
            } else {
                appendOrderList(orders, orderString);
            }
        }
        orders.forEach(Sort.Order::ignoreCase);
        return orders;
    }

    public static void appendOrderList(List<Sort.Order> orders, String orderChildString) {
        if (orderChildString.contains(":")) {
            String[] orderChildArray = orderChildString.split(":");
            Optional<Sort.Direction> optionalDirection = Sort.Direction.fromOptionalString(orderChildArray[1]);
            if (optionalDirection.isPresent()) {
                switch (optionalDirection.get()) {
                    case ASC:
                        orders.add(Sort.Order.asc(orderChildArray[0]));
                        break;
                    case DESC:
                        orders.add(Sort.Order.desc(orderChildArray[0]));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
