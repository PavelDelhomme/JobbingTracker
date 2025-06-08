package com.delhomme.jobbingtrack.data.local.dao.event;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomRawQuery;
import androidx.room.RoomSQLiteQuery;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SimpleSQLiteQuery;
import com.delhomme.jobbingtrack.data.local.entities.event.EventEntity;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<EventEntity> __insertAdapterOfEventEntity;

  private final EntityDeleteOrUpdateAdapter<EventEntity> __updateAdapterOfEventEntity;

  public EventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfEventEntity = new EntityInsertAdapter<EventEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `events` (`id`,`userId`,`relatedObjectId`,`title`,`description`,`startDate`,`endDate`,`syncHash`,`type`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final EventEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUserId());
        }
        if (entity.getRelatedObjectId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRelatedObjectId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDescription());
        }
        if (entity.getStartDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getStartDate());
        }
        if (entity.getEndDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getEndDate());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSyncHash());
        }
        if (entity.getType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getType());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(10, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getArchivedAt());
        }
      }
    };
    this.__updateAdapterOfEventEntity = new EntityDeleteOrUpdateAdapter<EventEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `events` SET `id` = ?,`userId` = ?,`relatedObjectId` = ?,`title` = ?,`description` = ?,`startDate` = ?,`endDate` = ?,`syncHash` = ?,`type` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final EventEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUserId());
        }
        if (entity.getRelatedObjectId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRelatedObjectId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDescription());
        }
        if (entity.getStartDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getStartDate());
        }
        if (entity.getEndDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getEndDate());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSyncHash());
        }
        if (entity.getType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getType());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(10, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final EventEntity event, final Continuation<? super Unit> $completion) {
    if (event == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfEventEntity.insert(_connection, event);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final EventEntity event, final Continuation<? super Unit> $completion) {
    if (event == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfEventEntity.handle(_connection, event);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<EventEntity>> getAllForUser(final String userId) {
    final String _sql = "\n"
            + "    SELECT * FROM events\n"
            + "     WHERE userId = ?\n"
            + "    ORDER BY startDate DESC\n"
            + "  ";
    return FlowUtil.createFlow(__db, false, new String[] {"events"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfRelatedObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relatedObjectId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<EventEntity> _result = new ArrayList<EventEntity>();
        while (_stmt.step()) {
          final EventEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpRelatedObjectId;
          if (_stmt.isNull(_columnIndexOfRelatedObjectId)) {
            _tmpRelatedObjectId = null;
          } else {
            _tmpRelatedObjectId = _stmt.getText(_columnIndexOfRelatedObjectId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final Long _tmpStartDate;
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmpStartDate = null;
          } else {
            _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          }
          final Long _tmpEndDate;
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmpEndDate = null;
          } else {
            _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new EventEntity(_tmpId,_tmpUserId,_tmpRelatedObjectId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpSyncHash,_tmpType,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<EventEntity>> getAllActiveForUser(final String userId) {
    final String _sql = "\n"
            + "    SELECT * FROM events\n"
            + "     WHERE userId = ?\n"
            + "       AND isDeleted = 0\n"
            + "       AND isArchived = 0\n"
            + "    ORDER BY startDate DESC\n"
            + "  ";
    return FlowUtil.createFlow(__db, false, new String[] {"events"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfRelatedObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relatedObjectId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<EventEntity> _result = new ArrayList<EventEntity>();
        while (_stmt.step()) {
          final EventEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpRelatedObjectId;
          if (_stmt.isNull(_columnIndexOfRelatedObjectId)) {
            _tmpRelatedObjectId = null;
          } else {
            _tmpRelatedObjectId = _stmt.getText(_columnIndexOfRelatedObjectId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final Long _tmpStartDate;
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmpStartDate = null;
          } else {
            _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          }
          final Long _tmpEndDate;
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmpEndDate = null;
          } else {
            _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new EventEntity(_tmpId,_tmpUserId,_tmpRelatedObjectId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpSyncHash,_tmpType,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<EventEntity>> getArchivedForUser(final String userId) {
    final String _sql = "\n"
            + "    SELECT * FROM events\n"
            + "     WHERE userId = ?\n"
            + "       AND isArchived = 1\n"
            + "       AND isDeleted = 0\n"
            + "    ORDER BY startDate DESC\n"
            + "  ";
    return FlowUtil.createFlow(__db, false, new String[] {"events"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfRelatedObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relatedObjectId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<EventEntity> _result = new ArrayList<EventEntity>();
        while (_stmt.step()) {
          final EventEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpRelatedObjectId;
          if (_stmt.isNull(_columnIndexOfRelatedObjectId)) {
            _tmpRelatedObjectId = null;
          } else {
            _tmpRelatedObjectId = _stmt.getText(_columnIndexOfRelatedObjectId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final Long _tmpStartDate;
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmpStartDate = null;
          } else {
            _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          }
          final Long _tmpEndDate;
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmpEndDate = null;
          } else {
            _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new EventEntity(_tmpId,_tmpUserId,_tmpRelatedObjectId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpSyncHash,_tmpType,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<EventEntity>> getDeletedForUser(final String userId) {
    final String _sql = "\n"
            + "    SELECT * FROM events\n"
            + "     WHERE userId = ?\n"
            + "       AND isDeleted = 1\n"
            + "    ORDER BY startDate DESC\n"
            + "  ";
    return FlowUtil.createFlow(__db, false, new String[] {"events"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfRelatedObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relatedObjectId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<EventEntity> _result = new ArrayList<EventEntity>();
        while (_stmt.step()) {
          final EventEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpRelatedObjectId;
          if (_stmt.isNull(_columnIndexOfRelatedObjectId)) {
            _tmpRelatedObjectId = null;
          } else {
            _tmpRelatedObjectId = _stmt.getText(_columnIndexOfRelatedObjectId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final Long _tmpStartDate;
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmpStartDate = null;
          } else {
            _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          }
          final Long _tmpEndDate;
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmpEndDate = null;
          } else {
            _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new EventEntity(_tmpId,_tmpUserId,_tmpRelatedObjectId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpSyncHash,_tmpType,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<EventEntity> getByIdForUser(final String id, final String userId) {
    final String _sql = "\n"
            + "    SELECT * FROM events\n"
            + "     WHERE id = ?\n"
            + "       AND userId = ?\n"
            + "  ";
    return FlowUtil.createFlow(__db, false, new String[] {"events"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfRelatedObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "relatedObjectId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final EventEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpRelatedObjectId;
          if (_stmt.isNull(_columnIndexOfRelatedObjectId)) {
            _tmpRelatedObjectId = null;
          } else {
            _tmpRelatedObjectId = _stmt.getText(_columnIndexOfRelatedObjectId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final Long _tmpStartDate;
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmpStartDate = null;
          } else {
            _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          }
          final Long _tmpEndDate;
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmpEndDate = null;
          } else {
            _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _result = new EventEntity(_tmpId,_tmpUserId,_tmpRelatedObjectId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpSyncHash,_tmpType,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object archive(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("    UPDATE events");
    _stringBuilder.append("\n");
    _stringBuilder.append("     SET isArchived = 1");
    _stringBuilder.append("\n");
    _stringBuilder.append("     WHERE id   IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    _stringBuilder.append("\n");
    _stringBuilder.append("       AND userId = ");
    _stringBuilder.append("?");
    _stringBuilder.append("\n");
    _stringBuilder.append("  ");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object softDelete(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("    UPDATE events");
    _stringBuilder.append("\n");
    _stringBuilder.append("     SET isDeleted = 1");
    _stringBuilder.append("\n");
    _stringBuilder.append("     WHERE id   IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    _stringBuilder.append("\n");
    _stringBuilder.append("       AND userId = ");
    _stringBuilder.append("?");
    _stringBuilder.append("\n");
    _stringBuilder.append("  ");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object restore(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("    UPDATE events");
    _stringBuilder.append("\n");
    _stringBuilder.append("     SET isDeleted = 0");
    _stringBuilder.append("\n");
    _stringBuilder.append("     WHERE id   IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    _stringBuilder.append("\n");
    _stringBuilder.append("       AND userId = ");
    _stringBuilder.append("?");
    _stringBuilder.append("\n");
    _stringBuilder.append("  ");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteForever(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("    DELETE FROM events");
    _stringBuilder.append("\n");
    _stringBuilder.append("     WHERE id   IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    _stringBuilder.append("\n");
    _stringBuilder.append("       AND userId = ");
    _stringBuilder.append("?");
    _stringBuilder.append("\n");
    _stringBuilder.append("  ");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForUser(final String userId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "\n"
            + "    DELETE FROM events\n"
            + "     WHERE userId = ?\n"
            + "  ";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Flow<List<EventEntity>> getByDateRange(final SimpleSQLiteQuery query) {
    final RoomRawQuery _rawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery();
    final String _sql = _rawQuery.getSql();
    return FlowUtil.createFlow(__db, false, new String[] {"events"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _rawQuery.getBindingFunction().invoke(_stmt);
        final List<EventEntity> _result = new ArrayList<EventEntity>();
        while (_stmt.step()) {
          final EventEntity _item;
          _item = __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesEventEntity(_stmt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public String getTableName() {
    return EventDao.DefaultImpls.getTableName(EventDao_Impl.this);
  }

  @Override
  public String getDateColumn() {
    return EventDao.DefaultImpls.getDateColumn(EventDao_Impl.this);
  }

  @Override
  public Flow<List<EventEntity>> getByDateRangeForUser(final String userId, final long start,
      final long end) {
    return EventDao.DefaultImpls.getByDateRangeForUser(EventDao_Impl.this, userId, start, end);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private EventEntity __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesEventEntity(
      @NonNull final SQLiteStatement statement) {
    final EventEntity _entity;
    final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndex(statement, "id");
    final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndex(statement, "userId");
    final int _columnIndexOfRelatedObjectId = SQLiteStatementUtil.getColumnIndex(statement, "relatedObjectId");
    final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndex(statement, "title");
    final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndex(statement, "description");
    final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndex(statement, "startDate");
    final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndex(statement, "endDate");
    final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndex(statement, "syncHash");
    final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndex(statement, "type");
    final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndex(statement, "isArchived");
    final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndex(statement, "isDeleted");
    final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndex(statement, "createdAt");
    final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndex(statement, "updatedAt");
    final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndex(statement, "deletedAt");
    final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndex(statement, "archivedAt");
    final String _tmpId;
    if (_columnIndexOfId == -1) {
      _tmpId = null;
    } else {
      if (statement.isNull(_columnIndexOfId)) {
        _tmpId = null;
      } else {
        _tmpId = statement.getText(_columnIndexOfId);
      }
    }
    final String _tmpUserId;
    if (_columnIndexOfUserId == -1) {
      _tmpUserId = null;
    } else {
      if (statement.isNull(_columnIndexOfUserId)) {
        _tmpUserId = null;
      } else {
        _tmpUserId = statement.getText(_columnIndexOfUserId);
      }
    }
    final String _tmpRelatedObjectId;
    if (_columnIndexOfRelatedObjectId == -1) {
      _tmpRelatedObjectId = null;
    } else {
      if (statement.isNull(_columnIndexOfRelatedObjectId)) {
        _tmpRelatedObjectId = null;
      } else {
        _tmpRelatedObjectId = statement.getText(_columnIndexOfRelatedObjectId);
      }
    }
    final String _tmpTitle;
    if (_columnIndexOfTitle == -1) {
      _tmpTitle = null;
    } else {
      if (statement.isNull(_columnIndexOfTitle)) {
        _tmpTitle = null;
      } else {
        _tmpTitle = statement.getText(_columnIndexOfTitle);
      }
    }
    final String _tmpDescription;
    if (_columnIndexOfDescription == -1) {
      _tmpDescription = null;
    } else {
      if (statement.isNull(_columnIndexOfDescription)) {
        _tmpDescription = null;
      } else {
        _tmpDescription = statement.getText(_columnIndexOfDescription);
      }
    }
    final Long _tmpStartDate;
    if (_columnIndexOfStartDate == -1) {
      _tmpStartDate = null;
    } else {
      if (statement.isNull(_columnIndexOfStartDate)) {
        _tmpStartDate = null;
      } else {
        _tmpStartDate = statement.getLong(_columnIndexOfStartDate);
      }
    }
    final Long _tmpEndDate;
    if (_columnIndexOfEndDate == -1) {
      _tmpEndDate = null;
    } else {
      if (statement.isNull(_columnIndexOfEndDate)) {
        _tmpEndDate = null;
      } else {
        _tmpEndDate = statement.getLong(_columnIndexOfEndDate);
      }
    }
    final String _tmpSyncHash;
    if (_columnIndexOfSyncHash == -1) {
      _tmpSyncHash = null;
    } else {
      if (statement.isNull(_columnIndexOfSyncHash)) {
        _tmpSyncHash = null;
      } else {
        _tmpSyncHash = statement.getText(_columnIndexOfSyncHash);
      }
    }
    final String _tmpType;
    if (_columnIndexOfType == -1) {
      _tmpType = null;
    } else {
      if (statement.isNull(_columnIndexOfType)) {
        _tmpType = null;
      } else {
        _tmpType = statement.getText(_columnIndexOfType);
      }
    }
    final boolean _tmpIsArchived;
    if (_columnIndexOfIsArchived == -1) {
      _tmpIsArchived = false;
    } else {
      final int _tmp;
      _tmp = (int) (statement.getLong(_columnIndexOfIsArchived));
      _tmpIsArchived = _tmp != 0;
    }
    final boolean _tmpIsDeleted;
    if (_columnIndexOfIsDeleted == -1) {
      _tmpIsDeleted = false;
    } else {
      final int _tmp_1;
      _tmp_1 = (int) (statement.getLong(_columnIndexOfIsDeleted));
      _tmpIsDeleted = _tmp_1 != 0;
    }
    final long _tmpCreatedAt;
    if (_columnIndexOfCreatedAt == -1) {
      _tmpCreatedAt = 0;
    } else {
      _tmpCreatedAt = statement.getLong(_columnIndexOfCreatedAt);
    }
    final long _tmpUpdatedAt;
    if (_columnIndexOfUpdatedAt == -1) {
      _tmpUpdatedAt = 0;
    } else {
      _tmpUpdatedAt = statement.getLong(_columnIndexOfUpdatedAt);
    }
    final Long _tmpDeletedAt;
    if (_columnIndexOfDeletedAt == -1) {
      _tmpDeletedAt = null;
    } else {
      if (statement.isNull(_columnIndexOfDeletedAt)) {
        _tmpDeletedAt = null;
      } else {
        _tmpDeletedAt = statement.getLong(_columnIndexOfDeletedAt);
      }
    }
    final Long _tmpArchivedAt;
    if (_columnIndexOfArchivedAt == -1) {
      _tmpArchivedAt = null;
    } else {
      if (statement.isNull(_columnIndexOfArchivedAt)) {
        _tmpArchivedAt = null;
      } else {
        _tmpArchivedAt = statement.getLong(_columnIndexOfArchivedAt);
      }
    }
    _entity = new EventEntity(_tmpId,_tmpUserId,_tmpRelatedObjectId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpSyncHash,_tmpType,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
    return _entity;
  }
}
