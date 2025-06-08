package com.delhomme.jobbingtrack.data.local.dao.interviews;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomRawQuery;
import androidx.room.RoomSQLiteQuery;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SimpleSQLiteQuery;
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity;
import com.delhomme.jobbingtrack.data.local.entities.InterviewContactCrossRef;
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity;
import com.delhomme.jobbingtrack.data.local.entities.InterviewWithContacts;
import java.lang.Class;
import java.lang.Integer;
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
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class InterviewDao_Impl implements InterviewDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<InterviewEntity> __insertAdapterOfEntretienEntity;

  private final EntityInsertAdapter<InterviewContactCrossRef> __insertAdapterOfEntretienContactCrossRef;

  private final EntityDeleteOrUpdateAdapter<InterviewEntity> __updateAdapterOfEntretienEntity;

  public InterviewDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfEntretienEntity = new EntityInsertAdapter<InterviewEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `entretiens` (`id`,`userId`,`candidatureId`,`companyId`,`dateTime`,`durationMinutes`,`location`,`style`,`type`,`preInterviewNotes`,`interviewNotes`,`postInterviewNotes`,`returnDate`,`testsNeeded`,`testsDeadline`,`syncHash`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final InterviewEntity entity) {
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
        if (entity.getApplicationId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getApplicationId());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCompanyId());
        }
        statement.bindLong(5, entity.getDateTime());
        if (entity.getDurationMinutes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getDurationMinutes());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getLocation());
        }
        if (entity.getStyle() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getStyle());
        }
        if (entity.getType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getType());
        }
        if (entity.getPreInterviewNotes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getPreInterviewNotes());
        }
        if (entity.getInterviewNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getInterviewNotes());
        }
        if (entity.getPostInterviewNotes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getPostInterviewNotes());
        }
        if (entity.getReturnDate() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getReturnDate());
        }
        final int _tmp = entity.getTestsNeeded() ? 1 : 0;
        statement.bindLong(14, _tmp);
        if (entity.getTestsDeadline() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getTestsDeadline());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getSyncHash());
        }
        final int _tmp_1 = entity.isArchived() ? 1 : 0;
        statement.bindLong(17, _tmp_1);
        final int _tmp_2 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(18, _tmp_2);
        statement.bindLong(19, entity.getCreatedAt());
        statement.bindLong(20, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(21);
        } else {
          statement.bindLong(21, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(22);
        } else {
          statement.bindLong(22, entity.getArchivedAt());
        }
      }
    };
    this.__insertAdapterOfEntretienContactCrossRef = new EntityInsertAdapter<InterviewContactCrossRef>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `EntretienContactCrossRef` (`entretienId`,`contactId`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final InterviewContactCrossRef entity) {
        if (entity.getEntretienId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getEntretienId());
        }
        if (entity.getContactId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getContactId());
        }
      }
    };
    this.__updateAdapterOfEntretienEntity = new EntityDeleteOrUpdateAdapter<InterviewEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `entretiens` SET `id` = ?,`userId` = ?,`candidatureId` = ?,`companyId` = ?,`dateTime` = ?,`durationMinutes` = ?,`location` = ?,`style` = ?,`type` = ?,`preInterviewNotes` = ?,`interviewNotes` = ?,`postInterviewNotes` = ?,`returnDate` = ?,`testsNeeded` = ?,`testsDeadline` = ?,`syncHash` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final InterviewEntity entity) {
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
        if (entity.getApplicationId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getApplicationId());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCompanyId());
        }
        statement.bindLong(5, entity.getDateTime());
        if (entity.getDurationMinutes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getDurationMinutes());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getLocation());
        }
        if (entity.getStyle() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getStyle());
        }
        if (entity.getType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getType());
        }
        if (entity.getPreInterviewNotes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getPreInterviewNotes());
        }
        if (entity.getInterviewNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getInterviewNotes());
        }
        if (entity.getPostInterviewNotes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getPostInterviewNotes());
        }
        if (entity.getReturnDate() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getReturnDate());
        }
        final int _tmp = entity.getTestsNeeded() ? 1 : 0;
        statement.bindLong(14, _tmp);
        if (entity.getTestsDeadline() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getTestsDeadline());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getSyncHash());
        }
        final int _tmp_1 = entity.isArchived() ? 1 : 0;
        statement.bindLong(17, _tmp_1);
        final int _tmp_2 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(18, _tmp_2);
        statement.bindLong(19, entity.getCreatedAt());
        statement.bindLong(20, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(21);
        } else {
          statement.bindLong(21, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(22);
        } else {
          statement.bindLong(22, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(23);
        } else {
          statement.bindText(23, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final InterviewEntity entretien,
      final Continuation<? super Unit> $completion) {
    if (entretien == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfEntretienEntity.insert(_connection, entretien);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object insertCrossRef(final InterviewContactCrossRef ref,
      final Continuation<? super Unit> $completion) {
    if (ref == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfEntretienContactCrossRef.insert(_connection, ref);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final InterviewEntity entretien,
      final Continuation<? super Unit> $completion) {
    if (entretien == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfEntretienEntity.handle(_connection, entretien);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<InterviewEntity>> getAll() {
    final String _sql = "SELECT * FROM entretiens ORDER BY dateTime DESC";
    return FlowUtil.createFlow(__db, true, new String[] {"entretiens"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<InterviewEntity> _result = new ArrayList<InterviewEntity>();
        while (_stmt.step()) {
          final InterviewEntity _item;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _item = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<InterviewWithContacts>> getAllActiveForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM entretiens\n"
            + "       WHERE userId    = ?\n"
            + "         AND isDeleted = 0\n"
            + "         AND isArchived= 0\n"
            + "      ORDER BY dateTime DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, true, new String[] {"EntretienContactCrossRef", "contacts",
        "entretiens"}, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ArrayMap<String, ArrayList<ContactEntity>> _collectionContacts = new ArrayMap<String, ArrayList<ContactEntity>>();
        while (_stmt.step()) {
          final String _tmpKey;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey = null;
          } else {
            _tmpKey = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey != null) {
            if (!_collectionContacts.containsKey(_tmpKey)) {
              _collectionContacts.put(_tmpKey, new ArrayList<ContactEntity>());
            }
          }
        }
        _stmt.reset();
        __fetchRelationshipcontactsAscomDelhommeJobbingtrackDataLocalEntitiesContactEntity(_connection, _collectionContacts);
        final List<InterviewWithContacts> _result = new ArrayList<InterviewWithContacts>();
        while (_stmt.step()) {
          final InterviewWithContacts _item;
          final InterviewEntity _tmpEntretien;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _tmpEntretien = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          final ArrayList<ContactEntity> _tmpContactsCollection;
          final String _tmpKey_1;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey_1 = null;
          } else {
            _tmpKey_1 = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey_1 != null) {
            _tmpContactsCollection = _collectionContacts.get(_tmpKey_1);
          } else {
            _tmpContactsCollection = new ArrayList<ContactEntity>();
          }
          _item = new InterviewWithContacts(_tmpEntretien,_tmpContactsCollection);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public LiveData<List<InterviewWithContacts>> getActiveWithContacts(final String userId) {
    final String _sql = "SELECT * FROM entretiens WHERE userId = ? AND isArchived = 0";
    return __db.getInvalidationTracker().createLiveData(new String[] {"EntretienContactCrossRef",
        "contacts", "entretiens"}, true, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ArrayMap<String, ArrayList<ContactEntity>> _collectionContacts = new ArrayMap<String, ArrayList<ContactEntity>>();
        while (_stmt.step()) {
          final String _tmpKey;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey = null;
          } else {
            _tmpKey = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey != null) {
            if (!_collectionContacts.containsKey(_tmpKey)) {
              _collectionContacts.put(_tmpKey, new ArrayList<ContactEntity>());
            }
          }
        }
        _stmt.reset();
        __fetchRelationshipcontactsAscomDelhommeJobbingtrackDataLocalEntitiesContactEntity(_connection, _collectionContacts);
        final List<InterviewWithContacts> _result = new ArrayList<InterviewWithContacts>();
        while (_stmt.step()) {
          final InterviewWithContacts _item;
          final InterviewEntity _tmpEntretien;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _tmpEntretien = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          final ArrayList<ContactEntity> _tmpContactsCollection;
          final String _tmpKey_1;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey_1 = null;
          } else {
            _tmpKey_1 = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey_1 != null) {
            _tmpContactsCollection = _collectionContacts.get(_tmpKey_1);
          } else {
            _tmpContactsCollection = new ArrayList<ContactEntity>();
          }
          _item = new InterviewWithContacts(_tmpEntretien,_tmpContactsCollection);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<InterviewEntity>> getArchivedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM entretiens\n"
            + "       WHERE userId    = ?\n"
            + "         AND isArchived= 1\n"
            + "      ORDER BY dateTime DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, true, new String[] {"entretiens"}, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<InterviewEntity> _result = new ArrayList<InterviewEntity>();
        while (_stmt.step()) {
          final InterviewEntity _item;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _item = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<InterviewEntity>> getDeletedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM entretiens\n"
            + "       WHERE userId    = ?\n"
            + "         AND isDeleted = 1\n"
            + "      ORDER BY dateTime DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, true, new String[] {"entretiens"}, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<InterviewEntity> _result = new ArrayList<InterviewEntity>();
        while (_stmt.step()) {
          final InterviewEntity _item;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _item = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<InterviewEntity> getByIdForUser(final String id, final String userId) {
    final String _sql = "SELECT * FROM entretiens WHERE id = ? AND userId = ?";
    return FlowUtil.createFlow(__db, true, new String[] {"entretiens"}, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final InterviewEntity _result;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _result = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
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
  public Flow<List<InterviewWithContacts>> getAllWithContactsForUser(final String userId) {
    final String _sql = "SELECT * FROM entretiens WHERE userId = ? AND isDeleted = 0 ORDER BY dateTime DESC";
    return FlowUtil.createFlow(__db, true, new String[] {"EntretienContactCrossRef", "contacts",
        "entretiens"}, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ArrayMap<String, ArrayList<ContactEntity>> _collectionContacts = new ArrayMap<String, ArrayList<ContactEntity>>();
        while (_stmt.step()) {
          final String _tmpKey;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey = null;
          } else {
            _tmpKey = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey != null) {
            if (!_collectionContacts.containsKey(_tmpKey)) {
              _collectionContacts.put(_tmpKey, new ArrayList<ContactEntity>());
            }
          }
        }
        _stmt.reset();
        __fetchRelationshipcontactsAscomDelhommeJobbingtrackDataLocalEntitiesContactEntity(_connection, _collectionContacts);
        final List<InterviewWithContacts> _result = new ArrayList<InterviewWithContacts>();
        while (_stmt.step()) {
          final InterviewWithContacts _item;
          final InterviewEntity _tmpEntretien;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _tmpEntretien = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          final ArrayList<ContactEntity> _tmpContactsCollection;
          final String _tmpKey_1;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey_1 = null;
          } else {
            _tmpKey_1 = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey_1 != null) {
            _tmpContactsCollection = _collectionContacts.get(_tmpKey_1);
          } else {
            _tmpContactsCollection = new ArrayList<ContactEntity>();
          }
          _item = new InterviewWithContacts(_tmpEntretien,_tmpContactsCollection);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<InterviewWithContacts> getByIdActiveWithContacts(final String id,
                                                               final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM entretiens\n"
            + "       WHERE id         = ?\n"
            + "         AND userId    = ?\n"
            + "         AND isDeleted = 0\n"
            + "         AND isArchived= 0\n"
            + "    ";
    return FlowUtil.createFlow(__db, true, new String[] {"EntretienContactCrossRef", "contacts",
        "entretiens"}, (_connection) -> {
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
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dateTime");
        final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "durationMinutes");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "style");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preInterviewNotes");
        final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "interviewNotes");
        final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postInterviewNotes");
        final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "returnDate");
        final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsNeeded");
        final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "testsDeadline");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ArrayMap<String, ArrayList<ContactEntity>> _collectionContacts = new ArrayMap<String, ArrayList<ContactEntity>>();
        while (_stmt.step()) {
          final String _tmpKey;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey = null;
          } else {
            _tmpKey = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey != null) {
            if (!_collectionContacts.containsKey(_tmpKey)) {
              _collectionContacts.put(_tmpKey, new ArrayList<ContactEntity>());
            }
          }
        }
        _stmt.reset();
        __fetchRelationshipcontactsAscomDelhommeJobbingtrackDataLocalEntitiesContactEntity(_connection, _collectionContacts);
        final InterviewWithContacts _result;
        if (_stmt.step()) {
          final InterviewEntity _tmpEntretien;
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
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final long _tmpDateTime;
          _tmpDateTime = _stmt.getLong(_columnIndexOfDateTime);
          final Integer _tmpDurationMinutes;
          if (_stmt.isNull(_columnIndexOfDurationMinutes)) {
            _tmpDurationMinutes = null;
          } else {
            _tmpDurationMinutes = (int) (_stmt.getLong(_columnIndexOfDurationMinutes));
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpStyle;
          if (_stmt.isNull(_columnIndexOfStyle)) {
            _tmpStyle = null;
          } else {
            _tmpStyle = _stmt.getText(_columnIndexOfStyle);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpPreInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPreInterviewNotes)) {
            _tmpPreInterviewNotes = null;
          } else {
            _tmpPreInterviewNotes = _stmt.getText(_columnIndexOfPreInterviewNotes);
          }
          final String _tmpInterviewNotes;
          if (_stmt.isNull(_columnIndexOfInterviewNotes)) {
            _tmpInterviewNotes = null;
          } else {
            _tmpInterviewNotes = _stmt.getText(_columnIndexOfInterviewNotes);
          }
          final String _tmpPostInterviewNotes;
          if (_stmt.isNull(_columnIndexOfPostInterviewNotes)) {
            _tmpPostInterviewNotes = null;
          } else {
            _tmpPostInterviewNotes = _stmt.getText(_columnIndexOfPostInterviewNotes);
          }
          final Long _tmpReturnDate;
          if (_stmt.isNull(_columnIndexOfReturnDate)) {
            _tmpReturnDate = null;
          } else {
            _tmpReturnDate = _stmt.getLong(_columnIndexOfReturnDate);
          }
          final boolean _tmpTestsNeeded;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfTestsNeeded));
          _tmpTestsNeeded = _tmp != 0;
          final Long _tmpTestsDeadline;
          if (_stmt.isNull(_columnIndexOfTestsDeadline)) {
            _tmpTestsDeadline = null;
          } else {
            _tmpTestsDeadline = _stmt.getLong(_columnIndexOfTestsDeadline);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_1 != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_2;
          _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_2 != 0;
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
          _tmpEntretien = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          final ArrayList<ContactEntity> _tmpContactsCollection;
          final String _tmpKey_1;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpKey_1 = null;
          } else {
            _tmpKey_1 = _stmt.getText(_columnIndexOfId);
          }
          if (_tmpKey_1 != null) {
            _tmpContactsCollection = _collectionContacts.get(_tmpKey_1);
          } else {
            _tmpContactsCollection = new ArrayList<ContactEntity>();
          }
          _result = new InterviewWithContacts(_tmpEntretien,_tmpContactsCollection);
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
  public Object clearContactsFor(final String entretienId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM EntretienContactCrossRef WHERE entretienId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (entretienId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, entretienId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object archive(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE entretiens SET isArchived = 1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
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
    _stringBuilder.append("UPDATE entretiens SET isDeleted  = 1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
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
    _stringBuilder.append("UPDATE entretiens SET isDeleted  = 0 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
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
    _stringBuilder.append("DELETE FROM entretiens WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
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
    final String _sql = "DELETE FROM entretiens WHERE userId = ?";
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
  public Flow<List<InterviewEntity>> getByDateRange(final SimpleSQLiteQuery query) {
    final RoomRawQuery _rawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery();
    final String _sql = _rawQuery.getSql();
    return FlowUtil.createFlow(__db, false, new String[] {"entretiens"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _rawQuery.getBindingFunction().invoke(_stmt);
        final List<InterviewEntity> _result = new ArrayList<InterviewEntity>();
        while (_stmt.step()) {
          final InterviewEntity _item;
          _item = __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesEntretienEntity(_stmt);
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
    return InterviewDao.DefaultImpls.getTableName(InterviewDao_Impl.this);
  }

  @Override
  public String getDateColumn() {
    return InterviewDao.DefaultImpls.getDateColumn(InterviewDao_Impl.this);
  }

  @Override
  public Flow<List<InterviewEntity>> getByDateRangeForUser(final String userId, final long start,
                                                           final long end) {
    return InterviewDao.DefaultImpls.getByDateRangeForUser(InterviewDao_Impl.this, userId, start, end);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipcontactsAscomDelhommeJobbingtrackDataLocalEntitiesContactEntity(
      @NonNull final SQLiteConnection _connection,
      @NonNull final ArrayMap<String, ArrayList<ContactEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    if (_map.size() > 999) {
      RelationUtil.recursiveFetchArrayMap(_map, true, (_tmpMap) -> {
        __fetchRelationshipcontactsAscomDelhommeJobbingtrackDataLocalEntitiesContactEntity(_connection, _tmpMap);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("SELECT `contacts`.`id` AS `id`,`contacts`.`userId` AS `userId`,`contacts`.`firstName` AS `firstName`,`contacts`.`lastName` AS `lastName`,`contacts`.`phone` AS `phone`,`contacts`.`email` AS `email`,`contacts`.`position` AS `position`,`contacts`.`department` AS `department`,`contacts`.`companyId` AS `companyId`,`contacts`.`candidatureId` AS `candidatureId`,`contacts`.`notes` AS `notes`,`contacts`.`syncHash` AS `syncHash`,`contacts`.`isArchived` AS `isArchived`,`contacts`.`isDeleted` AS `isDeleted`,`contacts`.`createdAt` AS `createdAt`,`contacts`.`updatedAt` AS `updatedAt`,`contacts`.`deletedAt` AS `deletedAt`,`contacts`.`archivedAt` AS `archivedAt`,_junction.`entretienId` FROM `EntretienContactCrossRef` AS _junction INNER JOIN `contacts` ON (_junction.`contactId` = `contacts`.`id`) WHERE _junction.`entretienId` IN (");
    final int _inputSize = __mapKeySet == null ? 1 : __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final SQLiteStatement _stmt = _connection.prepare(_sql);
    int _argIndex = 1;
    if (__mapKeySet == null) {
      _stmt.bindNull(_argIndex);
    } else {
      for (String _item : __mapKeySet) {
        if (_item == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, _item);
        }
        _argIndex++;
      }
    }
    try {
      // _junction.entretienId;
      final int _itemKeyIndex = 18;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _columnIndexOfId = 0;
      final int _columnIndexOfUserId = 1;
      final int _columnIndexOfFirstName = 2;
      final int _columnIndexOfLastName = 3;
      final int _columnIndexOfPhone = 4;
      final int _columnIndexOfEmail = 5;
      final int _columnIndexOfPosition = 6;
      final int _columnIndexOfDepartment = 7;
      final int _columnIndexOfCompanyId = 8;
      final int _columnIndexOfCandidatureId = 9;
      final int _columnIndexOfNotes = 10;
      final int _columnIndexOfSyncHash = 11;
      final int _columnIndexOfIsArchived = 12;
      final int _columnIndexOfIsDeleted = 13;
      final int _columnIndexOfCreatedAt = 14;
      final int _columnIndexOfUpdatedAt = 15;
      final int _columnIndexOfDeletedAt = 16;
      final int _columnIndexOfArchivedAt = 17;
      while (_stmt.step()) {
        final String _tmpKey;
        if (_stmt.isNull(_itemKeyIndex)) {
          _tmpKey = null;
        } else {
          _tmpKey = _stmt.getText(_itemKeyIndex);
        }
        if (_tmpKey != null) {
          final ArrayList<ContactEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final ContactEntity _item_1;
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
            final String _tmpFirstName;
            if (_stmt.isNull(_columnIndexOfFirstName)) {
              _tmpFirstName = null;
            } else {
              _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
            }
            final String _tmpLastName;
            if (_stmt.isNull(_columnIndexOfLastName)) {
              _tmpLastName = null;
            } else {
              _tmpLastName = _stmt.getText(_columnIndexOfLastName);
            }
            final String _tmpPhone;
            if (_stmt.isNull(_columnIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _stmt.getText(_columnIndexOfPhone);
            }
            final String _tmpEmail;
            if (_stmt.isNull(_columnIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _stmt.getText(_columnIndexOfEmail);
            }
            final String _tmpPosition;
            if (_stmt.isNull(_columnIndexOfPosition)) {
              _tmpPosition = null;
            } else {
              _tmpPosition = _stmt.getText(_columnIndexOfPosition);
            }
            final String _tmpDepartment;
            if (_stmt.isNull(_columnIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _stmt.getText(_columnIndexOfDepartment);
            }
            final String _tmpCompanyId;
            if (_stmt.isNull(_columnIndexOfCompanyId)) {
              _tmpCompanyId = null;
            } else {
              _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
            }
            final String _tmpCandidatureId;
            if (_stmt.isNull(_columnIndexOfCandidatureId)) {
              _tmpCandidatureId = null;
            } else {
              _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
            }
            final String _tmpNotes;
            if (_stmt.isNull(_columnIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _stmt.getText(_columnIndexOfNotes);
            }
            final String _tmpSyncHash;
            if (_stmt.isNull(_columnIndexOfSyncHash)) {
              _tmpSyncHash = null;
            } else {
              _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
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
            _item_1 = new ContactEntity(_tmpId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpPhone,_tmpEmail,_tmpPosition,_tmpDepartment,_tmpCompanyId,_tmpCandidatureId,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _stmt.close();
    }
  }

  private InterviewEntity __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesEntretienEntity(
      @NonNull final SQLiteStatement statement) {
    final InterviewEntity _entity;
    final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndex(statement, "id");
    final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndex(statement, "userId");
    final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndex(statement, "candidatureId");
    final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndex(statement, "companyId");
    final int _columnIndexOfDateTime = SQLiteStatementUtil.getColumnIndex(statement, "dateTime");
    final int _columnIndexOfDurationMinutes = SQLiteStatementUtil.getColumnIndex(statement, "durationMinutes");
    final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndex(statement, "location");
    final int _columnIndexOfStyle = SQLiteStatementUtil.getColumnIndex(statement, "style");
    final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndex(statement, "type");
    final int _columnIndexOfPreInterviewNotes = SQLiteStatementUtil.getColumnIndex(statement, "preInterviewNotes");
    final int _columnIndexOfInterviewNotes = SQLiteStatementUtil.getColumnIndex(statement, "interviewNotes");
    final int _columnIndexOfPostInterviewNotes = SQLiteStatementUtil.getColumnIndex(statement, "postInterviewNotes");
    final int _columnIndexOfReturnDate = SQLiteStatementUtil.getColumnIndex(statement, "returnDate");
    final int _columnIndexOfTestsNeeded = SQLiteStatementUtil.getColumnIndex(statement, "testsNeeded");
    final int _columnIndexOfTestsDeadline = SQLiteStatementUtil.getColumnIndex(statement, "testsDeadline");
    final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndex(statement, "syncHash");
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
    final String _tmpCandidatureId;
    if (_columnIndexOfCandidatureId == -1) {
      _tmpCandidatureId = null;
    } else {
      if (statement.isNull(_columnIndexOfCandidatureId)) {
        _tmpCandidatureId = null;
      } else {
        _tmpCandidatureId = statement.getText(_columnIndexOfCandidatureId);
      }
    }
    final String _tmpCompanyId;
    if (_columnIndexOfCompanyId == -1) {
      _tmpCompanyId = null;
    } else {
      if (statement.isNull(_columnIndexOfCompanyId)) {
        _tmpCompanyId = null;
      } else {
        _tmpCompanyId = statement.getText(_columnIndexOfCompanyId);
      }
    }
    final long _tmpDateTime;
    if (_columnIndexOfDateTime == -1) {
      _tmpDateTime = 0;
    } else {
      _tmpDateTime = statement.getLong(_columnIndexOfDateTime);
    }
    final Integer _tmpDurationMinutes;
    if (_columnIndexOfDurationMinutes == -1) {
      _tmpDurationMinutes = null;
    } else {
      if (statement.isNull(_columnIndexOfDurationMinutes)) {
        _tmpDurationMinutes = null;
      } else {
        _tmpDurationMinutes = (int) (statement.getLong(_columnIndexOfDurationMinutes));
      }
    }
    final String _tmpLocation;
    if (_columnIndexOfLocation == -1) {
      _tmpLocation = null;
    } else {
      if (statement.isNull(_columnIndexOfLocation)) {
        _tmpLocation = null;
      } else {
        _tmpLocation = statement.getText(_columnIndexOfLocation);
      }
    }
    final String _tmpStyle;
    if (_columnIndexOfStyle == -1) {
      _tmpStyle = null;
    } else {
      if (statement.isNull(_columnIndexOfStyle)) {
        _tmpStyle = null;
      } else {
        _tmpStyle = statement.getText(_columnIndexOfStyle);
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
    final String _tmpPreInterviewNotes;
    if (_columnIndexOfPreInterviewNotes == -1) {
      _tmpPreInterviewNotes = null;
    } else {
      if (statement.isNull(_columnIndexOfPreInterviewNotes)) {
        _tmpPreInterviewNotes = null;
      } else {
        _tmpPreInterviewNotes = statement.getText(_columnIndexOfPreInterviewNotes);
      }
    }
    final String _tmpInterviewNotes;
    if (_columnIndexOfInterviewNotes == -1) {
      _tmpInterviewNotes = null;
    } else {
      if (statement.isNull(_columnIndexOfInterviewNotes)) {
        _tmpInterviewNotes = null;
      } else {
        _tmpInterviewNotes = statement.getText(_columnIndexOfInterviewNotes);
      }
    }
    final String _tmpPostInterviewNotes;
    if (_columnIndexOfPostInterviewNotes == -1) {
      _tmpPostInterviewNotes = null;
    } else {
      if (statement.isNull(_columnIndexOfPostInterviewNotes)) {
        _tmpPostInterviewNotes = null;
      } else {
        _tmpPostInterviewNotes = statement.getText(_columnIndexOfPostInterviewNotes);
      }
    }
    final Long _tmpReturnDate;
    if (_columnIndexOfReturnDate == -1) {
      _tmpReturnDate = null;
    } else {
      if (statement.isNull(_columnIndexOfReturnDate)) {
        _tmpReturnDate = null;
      } else {
        _tmpReturnDate = statement.getLong(_columnIndexOfReturnDate);
      }
    }
    final boolean _tmpTestsNeeded;
    if (_columnIndexOfTestsNeeded == -1) {
      _tmpTestsNeeded = false;
    } else {
      final int _tmp;
      _tmp = (int) (statement.getLong(_columnIndexOfTestsNeeded));
      _tmpTestsNeeded = _tmp != 0;
    }
    final Long _tmpTestsDeadline;
    if (_columnIndexOfTestsDeadline == -1) {
      _tmpTestsDeadline = null;
    } else {
      if (statement.isNull(_columnIndexOfTestsDeadline)) {
        _tmpTestsDeadline = null;
      } else {
        _tmpTestsDeadline = statement.getLong(_columnIndexOfTestsDeadline);
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
    final boolean _tmpIsArchived;
    if (_columnIndexOfIsArchived == -1) {
      _tmpIsArchived = false;
    } else {
      final int _tmp_1;
      _tmp_1 = (int) (statement.getLong(_columnIndexOfIsArchived));
      _tmpIsArchived = _tmp_1 != 0;
    }
    final boolean _tmpIsDeleted;
    if (_columnIndexOfIsDeleted == -1) {
      _tmpIsDeleted = false;
    } else {
      final int _tmp_2;
      _tmp_2 = (int) (statement.getLong(_columnIndexOfIsDeleted));
      _tmpIsDeleted = _tmp_2 != 0;
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
    _entity = new InterviewEntity(_tmpId,_tmpUserId,_tmpCandidatureId,_tmpCompanyId,_tmpDateTime,_tmpDurationMinutes,_tmpLocation,_tmpStyle,_tmpType,_tmpPreInterviewNotes,_tmpInterviewNotes,_tmpPostInterviewNotes,_tmpReturnDate,_tmpTestsNeeded,_tmpTestsDeadline,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
    return _entity;
  }
}
